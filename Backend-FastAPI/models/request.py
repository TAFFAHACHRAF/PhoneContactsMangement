from pydantic import BaseModel, EmailStr
from typing import Optional


from typing import Optional

class ContactRequest(BaseModel):
    CONTACT_ID: Optional[int]
    CONTACT_FIRSTNAME: str = ""
    CONTACT_LASTNAME: str = ""
    CONTACT_EMAIL: str = ""
    CONTACT_PHONE: str = ""
    CONTACT_TYPE: str = ""
    CONTACT_GENDER: str = ""

