from pydantic import BaseModel, EmailStr
from typing import Optional


class ContactRequest(BaseModel):
    surname: str
    lastname: str 
    email: EmailStr 
    picture: str 
