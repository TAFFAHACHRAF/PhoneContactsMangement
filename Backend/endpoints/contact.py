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
async def add_contact(surname: str, lastname: str, email: str, picture:str):
    contact = Contact(surname, lastname, email, picture)
    session = database.get_db_session(engine)
    session.add(contact)
    session.commit()
    return Response(contact,200,"Contact added successfully", False)

@router.put("/{id}")
async def put_contact(id: int, surname: str, lastname: str, email: str, picture:str):
    session = database.get_db_session(engine)
    contact=Contact(id,surname,lastname,email,picture)
    contact_to_update = session.query(Contact).filter(Contact.id == id).first()

    contact_to_update.surname = surname
    contact_to_update.lastname = lastname
    contact_to_update.email = email
    contact_to_update.picture = picture
    session.commit()
    return Response(True, 200, "Contact updated successfully", False)
