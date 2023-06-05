from fastapi import APIRouter
from endpoints import contact

router = APIRouter()
router.include_router(contact.router)