from sqlalchemy.orm import declarative_base
from sqlalchemy import Column, Integer, String

Base = declarative_base()
class Contact(Base):
    __tablename__ = 'CONTACT'
    CONTACT_ID = Column(Integer, primary_key=True, index=True)
    CONTACT_SURNAME = Column(String, nullable=False)
    CONTACT_LASTNAME = Column(String, nullable=False)
    CONTACT_EMAIL = Column(String, unique=True, nullable=False)
    CONTACT_PICTURE = Column(String, nullable=False)

    def __init__(self, CONTACT_SURNAME: str, CONTACT_LASTNAME: str, CONTACT_EMAIL: str, CONTACT_PICTURE:str):
        self.CONTACT_ID=0
        self.CONTACT_SURNAME = CONTACT_SURNAME
        self.CONTACT_LASTNAME = CONTACT_LASTNAME
        self.CONTACT_EMAIL = CONTACT_EMAIL
        self.CONTACT_PICTURE = CONTACT_PICTURE  

