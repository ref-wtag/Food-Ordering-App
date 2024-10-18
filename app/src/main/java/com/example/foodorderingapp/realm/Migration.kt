package io.realm.examples.realmmigrationexample.model

import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration

class Migration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        var oldVersion = oldVersion
        val schema = realm.schema

        if (oldVersion == 0L) {
            val personSchema = schema["Person"]

            personSchema?.addField("fullName", String::class.java, FieldAttribute.REQUIRED)
                ?.transform { obj ->
                    obj["fullName"] = obj.getString("firstName") + " " + obj.getString("lastName")
                }
                ?.removeField("firstName")
                ?.removeField("lastName")
            oldVersion++
        }

        if (oldVersion == 1L) {
            val petSchema = schema.create("Pet")
                .addField("name", String::class.java, FieldAttribute.REQUIRED)
                .addField("type", String::class.java, FieldAttribute.REQUIRED)

            schema["Person"]
                ?.addRealmListField("pets", petSchema)
                ?.transform { obj ->
                    if (obj.getString("fullName") == "JP McDonald") {
                        val pet = realm.createObject("Pet")
                        pet.setString("name", "Jimbo")
                        pet.setString("type", "dog")
                        obj.getList("pets").add(pet)
                    }
                }
            oldVersion++
        }

        if (oldVersion == 2L) {
            val personSchema = schema["Person"]
            personSchema!!.setNullable("fullName", true) // fullName is nullable now.

            // Change type from String to int
            schema["Pet"]
                ?.addField("type_tmp", Int::class.javaPrimitiveType)
                ?.transform { obj ->
                    val oldType = obj.getString("type")
                    if (oldType == "dog") {
                        obj.setLong("type_tmp", 1)
                    } else if (oldType == "cat") {
                        obj.setInt("type_tmp", 2)
                    } else if (oldType == "hamster") {
                        obj.setInt("type_tmp", 3)
                    }
                }
                ?.removeField("type")
                ?.renameField("type_tmp", "type")
            oldVersion++
        }
    }

//    override fun equals(obj: Any?): Boolean {
//        return obj != null && obj is io.realm.examples.realmmigrationexample.model.Migration
//    }
//
//    override fun hashCode(): I@nt {
//        return io.realm.examples.realmmigrationexample.model.Migration::class.java.hashCode()
//    }
}