from fastapi import APIRouter
from models.request import ContactRequest
from models.response import Response
from models.models import Contact
from db.database import Database
from sqlalchemy import and_, desc
from sqlalchemy import Integer


# APIRouter creates path operations for product module
router = APIRouter(
    prefix="/contacts",
    tags=["Contact"],
    responses={404: {"description": "Not found"}},
)

database = Database()
engine = database.get_db_connection()

@router.get("/")
async def read_all_contacts():
    session = database.get_db_session(engine)
    data = session.query(Contact).all()
    return Response(data, 200, "Contacts retrieved successfully.", False)

@router.post("/")
async def add_contact(CONTACT_FIRSTNAME: str, CONTACT_LASTNAME: str, CONTACT_EMAIL:str,CONTACT_PHONE:str, CONTACT_TYPE:str, CONTACT_GENDER:str):
    contact = Contact(CONTACT_FIRSTNAME, CONTACT_LASTNAME, CONTACT_EMAIL, CONTACT_PHONE, CONTACT_TYPE, CONTACT_GENDER)
    session = database.get_db_session(engine)
    session.add(contact)
    session.commit()
    print(contact)
    return Response(contact,200,"Contact added successfully", False)

@router.put("/{id}")
async def update_contact(id: int, CONTACT_FIRSTNAME: str = None, CONTACT_LASTNAME: str = None, CONTACT_EMAIL: str = None, CONTACT_PHONE: str = None, CONTACT_TYPE: str = None, CONTACT_GENDER: str = None):
    session = database.get_db_session(engine)
    contact_to_update = session.query(Contact).filter(Contact.CONTACT_ID == id).first()
    print(contact_to_update.toString())
    if contact_to_update is None:
        return Response(False, 404, "Contact not found", False)

    if CONTACT_FIRSTNAME is not None:
        contact_to_update.CONTACT_FIRSTNAME = CONTACT_FIRSTNAME

    if CONTACT_LASTNAME is not None:
        contact_to_update.CONTACT_LASTNAME = CONTACT_LASTNAME

    if CONTACT_EMAIL is not None:
        contact_to_update.CONTACT_EMAIL = CONTACT_EMAIL

    if CONTACT_PHONE is not None:
        contact_to_update.CONTACT_PHONE = CONTACT_PHONE

    if CONTACT_TYPE is not None:
        contact_to_update.CONTACT_TYPE = CONTACT_TYPE

    if CONTACT_GENDER is not None:
        contact_to_update.CONTACT_GENDER = CONTACT_GENDER

    session.commit()
    return Response(True, 200, "Contact updated successfully", False)
