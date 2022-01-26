from django.http.response import HttpResponse
from django.shortcuts import render
import pandas as pd
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import chromedriver_autoinstaller #used to auto-install chromedriver if current version of chrome is different
import time
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.common.by import By
import pprint
from bs4 import BeautifulSoup
import re
import csv


# Create your views here.
def index(request):
    return render(request, 'home.html')

def get_tweet_data(tweet):
    username = tweet.find_element_by_xpath('.//span').text
    try:
        handle = tweet.find_element_by_xpath('.//span[contains(text(), "@")]').text
    except NoSuchElementException:
        return
    try:
        postdate = tweet.find_element_by_xpath('.//time').get_attribute('datetime')
    except NoSuchElementException:
        return
    
    comment = tweet.find_element_by_xpath('.//div[2]/div[2]/div[1]').text
    responding = tweet.find_element_by_xpath('.//div[2]/div[2]/div[2]').text
    text = comment + responding
    
    reply_cnt = tweet.find_element_by_xpath('.//div[@data-testid="reply"]').text
    if(reply_cnt == ''):
        reply_cnt = 0
    retweet_cnt = tweet.find_element_by_xpath('.//div[@data-testid="retweet"]').text
    if(retweet_cnt == ''):
        retweet_cnt = 0
    like_cnt = tweet.find_element_by_xpath('.//div[@data-testid="like"]').text
    if(like_cnt == ''):
        like_cnt = 0

    tweets = (username, handle, postdate, text, like_cnt, retweet_cnt,reply_cnt)
    return tweets    

def get_tweets(request):
    if request.method == 'GET':
        chromedriver_autoinstaller.install()  
        driver = webdriver.Chrome()
        url = "https://twitter.com/explore"
        driver.get(url)

        """I COULD USE A REAL TWITTER ACCOUNT JUST FOR TEST BUT DUE TO A LOT OF TESTS IN ORDER TO FILTER THE DATA CORRECTLY, 
        SOMETIMES I NEED TO PUT PHONE NUMBER IN ORDER TO VERIFY MY IDENTITY. SO, I DIRECTLY USED EXPLORE PAGE OF TWITTER""" 


        # time.sleep(2)
        # sig_but = driver.find_element_by_xpath('//*[@id="react-root"]/div/div/div[2]/main/div/div/div[1]/div[1]/div/div[3]/div[5]/a')
        # time.sleep(1)
        # sig_but.click()

        # time.sleep(1)
        # username = driver.find_element_by_xpath('//input[@name="text"]')
        # time.sleep(1)
        # username.send_keys('RAtestuser')
        # time.sleep(1)
        # username.send_keys(Keys.RETURN)

        # time.sleep(3)
        # password = driver.find_element_by_xpath('//input[@name="password"]')
        # time.sleep(1)
        # password.send_keys('Reatest1.')
        # time.sleep(1)
        # password.send_keys(Keys.RETURN)

        #write in search
        time.sleep(1)
        input_search = driver.find_element_by_xpath('//*[@id="react-root"]/div/div/div[2]/main/div/div/div/div/div/div[1]/div[1]/div/div/div/div/div[1]/div[2]/div/div/div/form/div[1]/div/div/label/div[2]/div/input')
        input_search.send_keys("request for startup")
        input_search.send_keys(Keys.ENTER)
        time.sleep(2)

        #latest 
        driver.find_element_by_xpath('//*[@id="react-root"]/div/div/div[2]/main/div/div/div/div/div/div[1]/div[2]/nav/div/div[2]/div/div[2]/a').click()

        data = []
        tweet_ids = set()
        last_position = driver.execute_script("return window.pageYOffset;")
        scrolling = True

        i = 0
        #SCRAPED 5 PAGES FOR TESTS
        #while scrolling:
        for i in range(5):
            page_cards = driver.find_elements_by_xpath('//article[@data-testid="tweet"]')
            for card in page_cards[-15:]:
                tweet = get_tweet_data(card)
                data.append(tweet)
                    
            scroll_attempt = 0
            while True:
                #check scroll position
                driver.execute_script('window.scrollTo(0, document.body.scrollHeight);')
                time.sleep(2)
                curr_position = driver.execute_script("return window.pageYOffset;")
                if last_position == curr_position:
                    scroll_attempt += 1
                    
                    #end of scroll region
                    if scroll_attempt >= 3:
                        scrolling = False
                        break
                    else:
                        time.sleep(2) #attempt another scroll
                else:
                    last_position = curr_position
                    break

        # close the web driver
        driver.close()


        #save to csv
        with open('tweets.csv', 'w', newline='', encoding='utf-8') as f:
            header = ['Author', 'Handle of Author', 'Date', 'Tweet', 'Likes', 'Retweets', 'Discussions']
            writer = csv.writer(f)
            writer.writerow(header)
            writer.writerows(data)

def load(request):
    df = pd.read_csv("tableview/static/csv/20_Startups.csv")
    #'tableview/static/csv/20_Startups.csv' is the django 
    # directory where csv file exist.
    # Manipulate DataFrame using to_html() function
    geeks_object = df.to_html()
    return HttpResponse(geeks_object)
