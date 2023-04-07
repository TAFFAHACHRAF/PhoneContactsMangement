from sqlalchemy.orm import declarative_base
from sqlalchemy import Column, Integer, String

Base = declarative_base()

class Contact(Base):
    __tablename__ = "CONTACT"

    CONTACT_ID = Column(Integer, primary_key=True, index=True)
    CONTACT_SURNAME = Column(String)
    CONTACT_LASTNAME = Column(String)
    CONTACT_EMAIL = Column(String)
    CONTACT_PICTURE = Column(String)
