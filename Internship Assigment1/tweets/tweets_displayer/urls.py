from django.urls import path
from django.urls.resolvers import URLPattern
from . import views


urlpatterns = [
    path('',views.index, name='home'), 
    path('get_tweets/', views.get_tweets, name='get_tweets')
]