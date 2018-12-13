# Hibernate Project
## Programming labs
This code isn't well-written. Some things here aren't working as expected.
###Known issues:
* Can't test Arena class in SaveToDBTest - after commenting out everything, which involves
objects of this class, the test works fine. Otherwise, it raises JsonMappingException
"Already had POJO for id". Not sure, why this happens, as Arena is not the only model,
which has one-to-one relationships, but the only one, which crashes.
* Program doesn't raise error while deserializing when some fields are present, but empty (?).
* Code could be written in more elegant, readable way.