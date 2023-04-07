from fastapi import FastAPI
import json
import uvicorn

fakeData = [
        {
            "id" : "0",
            "name" : "achraf",
            "phone" : "0684134782",
            "email" : "taffahachraf184@gmail.com"
        },
         {
            "id" : "0",
            "name" : "achraf",
            "phone" : "0684134782",
            "email" : "taffahachraf184@gmail.com"
        },
         {
            "id" : "0",
            "name" : "achraf",
            "phone" : "0684134782",
            "email" : "taffahachraf184@gmail.com"
        },
         {
            "id" : "0",
            "name" : "achraf",
            "phone" : "0684134782",
            "email" : "taffahachraf184@gmail.com"
        },
         {
            "id" : "0",
            "name" : "achraf",
            "phone" : "0684134782",
            "email" : "taffahachraf184@gmail.com"
        }
    ]

app = FastAPI()

# Hello World route
@app.get("/contacts")
def read_root():
    return fakeData
