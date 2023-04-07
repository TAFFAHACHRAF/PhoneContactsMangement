from fastapi import APIRouter
from models.request import ContactRequest
from models.response import Response
from models.models import Contact
from db.database import Database
from sqlalchemy import and_, desc

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