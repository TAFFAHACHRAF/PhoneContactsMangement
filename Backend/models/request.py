from pydantic import BaseModel, EmailStr
from typing import Optional


class ContactRequest(BaseModel):
    CONTACT_ID : str
    CONTACT_FIRSTNAME : str
    CONTACT_LASTNAME : str
    CONTACT_EMAIL : str
    CONTACT_PHONE : str
    CONTACT_TYPE : str
    CONTACT_GENDER : str
