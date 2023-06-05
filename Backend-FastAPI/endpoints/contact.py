from fastapi import APIRouter, Request
from models.request import ContactRequest
from models.response import Response
from models.models import Contact
from db.database import Database

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

@router.get("/{id}")
async def read_contact_by_id(id: int):
    session = database.get_db_session(engine)
    contact = session.query(Contact).filter(Contact.CONTACT_ID == id).first()
    if contact is None:
        return Response(None, 404, "Contact not found", False)
    return Response(contact, 200, "Contact retrieved successfully", False)

@router.post("/")
async def add_contact(request: Request):
    contact_data = await request.json()
    contact_request = ContactRequest(**contact_data)
    
    contact = Contact(
        CONTACT_FIRSTNAME=contact_request.CONTACT_FIRSTNAME,
        CONTACT_LASTNAME=contact_request.CONTACT_LASTNAME,
        CONTACT_EMAIL=contact_request.CONTACT_EMAIL,
        CONTACT_PHONE=contact_request.CONTACT_PHONE,
        CONTACT_TYPE=contact_request.CONTACT_TYPE,
        CONTACT_GENDER=contact_request.CONTACT_GENDER
    )
    
    session = database.get_db_session(engine)
    session.add(contact)
    session.commit()
    return Response(contact, 200, "Contact added successfully", False)

@router.post("/")
async def add_contact(contact_request: ContactRequest):
    contact = Contact(
        CONTACT_FIRSTNAME=contact_request.CONTACT_FIRSTNAME,
        CONTACT_LASTNAME=contact_request.CONTACT_LASTNAME,
        CONTACT_EMAIL=contact_request.CONTACT_EMAIL,
        CONTACT_PHONE=contact_request.CONTACT_PHONE,
        CONTACT_TYPE=contact_request.CONTACT_TYPE,
        CONTACT_GENDER=contact_request.CONTACT_GENDER
    )
    
    session = database.get_db_session(engine)
    session.add(contact)
    session.commit()
    return Response(contact, 200, "Contact added successfully", False)


@router.delete("/{id}")
async def delete_contact(id: int):
    session = database.get_db_session(engine)
    contact_to_delete = session.query(Contact).filter(Contact.CONTACT_ID == id).first()
    if contact_to_delete is None:
        return Response(None, 404, "Contact not found", False)

    session.delete(contact_to_delete)
    session.commit()
    return Response(True, 200, "Contact deleted successfully", False)
