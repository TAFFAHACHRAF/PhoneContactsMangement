from sqlalchemy.orm import declarative_base 
from sqlalchemy import Column, Integer, String

Base = declarative_base()

# Contact table
class Contact(Base):
    __tablename__ = 'CONTACT'
    CONTACT_ID = Column(Integer, primary_key=True, index=True, autoincrement=True)
    CONTACT_FIRSTNAME = Column(String, nullable=False)
    CONTACT_LASTNAME = Column(String, nullable=False)
    CONTACT_EMAIL = Column(String, unique=True, nullable=False)
    CONTACT_PHONE = Column(String, unique=True, nullable=False)
    CONTACT_TYPE = Column(String, unique=True, nullable=False)
    CONTACT_GENDER = Column(String, unique=True, nullable=False)
    

    def __init__(self, CONTACT_FIRSTNAME: str, CONTACT_LASTNAME: str, CONTACT_EMAIL: str, CONTACT_PHONE:str, CONTACT_TYPE:str,CONTACT_GENDER:str):
        self.CONTACT_FIRSTNAME = CONTACT_FIRSTNAME
        self.CONTACT_LASTNAME = CONTACT_LASTNAME
        self.CONTACT_EMAIL = CONTACT_EMAIL
        self.CONTACT_PHONE = CONTACT_PHONE
        self.CONTACT_TYPE = CONTACT_TYPE
        self.CONTACT_GENDER = CONTACT_GENDER  

    def toString(self):
        contact = "ID : " + str(self.CONTACT_ID) + " FIRSTNAME : " + self.CONTACT_FIRSTNAME + " LASTNAME : " + self.CONTACT_LASTNAME + " EMAIL: " + self.CONTACT_EMAIL + " PHONE: " + self.CONTACT_PHONE + " TYPE: " + self.CONTACT_TYPE + " GENDER: " + self.CONTACT_GENDER
        print(contact)
