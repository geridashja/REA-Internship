from typing import Match
from django.db import models

import tweets_displayer

# Create your models here.

class csv_data(models.Model):
    author = models.CharField('Author', max_length=120)
    handle_of_author = models.CharField('Username',max_length=120)
    date = models.DateField('Date')
    tweet = models.CharField('Date')
    likes =  models.IntegerField("Likes")
    retweets =  models.IntegerField("Retweets")
    discussions =  models.IntegerField("Discussions")

    def __str__(self):
        return self.name