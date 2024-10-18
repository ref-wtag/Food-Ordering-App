package com.example.foodorderingapp.realm;

 import io.realm.DynamicRealm;
 import io.realm.DynamicRealmObject;
 import io.realm.FieldAttribute;
 import io.realm.RealmMigration;
 import io.realm.RealmObjectSchema;
 import io.realm.RealmSchema;


 public class Migration implements RealmMigration {

     @Override
     public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
         // During a migration, a DynamicRealm is exposed. A DynamicRealm is an untyped variant of a normal Realm, but
         // with the same object creation and query capabilities.
         // A DynamicRealm uses Strings instead of Class references because the Classes might not even exist or have been
         // renamed.

         // Access the Realm schema in order to create, modify or delete classes and their fields.
         RealmSchema schema = realm.getSchema();

         // Migrate from version 0 to version 1
         if (oldVersion == 0) {
             RealmObjectSchema personSchema = schema.get("Person");

             // Combine 'firstName' and 'lastName' in a new field called 'fullName'
             personSchema
                     .addField("fullName", String.class, FieldAttribute.REQUIRED)
                     .transform(new RealmObjectSchema.Function() {
                         @Override
                         public void apply(DynamicRealmObject obj) {
                             obj.set("fullName", obj.getString("firstName") + " " + obj.getString("lastName"));
                         }
                     })
                     .removeField("firstName")
                     .removeField("lastName");
             oldVersion++;
         }
         // Migrate from version 1 to version 2
         if (oldVersion == 1) {

             // Create a new class
             RealmObjectSchema petSchema = schema.create("Pet")
                     .addField("name", String.class, FieldAttribute.REQUIRED)
                     .addField("type", String.class, FieldAttribute.REQUIRED);

             // Add a new field to an old class and populate it with initial data
             schema.get("Person")
                     .addRealmListField("pets", petSchema)
                     .transform(new RealmObjectSchema.Function() {
                         @Override
                         public void apply(DynamicRealmObject obj) {
                             if (obj.getString("fullName").equals("JP McDonald")) {
                                 DynamicRealmObject pet = realm.createObject("Pet");
                                 pet.setString("name", "Jimbo");
                                 pet.setString("type", "dog");
                                 obj.getList("pets").add(pet);
                             }
                         }
                     });
             oldVersion++;
         }


         // Migrate from version 2 to version 3
         if (oldVersion == 2) {
             RealmObjectSchema personSchema = schema.get("Person");
             personSchema.setNullable("fullName", true); // fullName is nullable now.

             // Change type from String to int
             schema.get("Pet")
                     .addField("type_tmp", int.class)
                     .transform(new RealmObjectSchema.Function() {
                         @Override
                         public void apply(DynamicRealmObject obj) {
                             String oldType = obj.getString("type");
                             if (oldType.equals("dog")) {
                                 obj.setLong("type_tmp", 1);
                             } else if (oldType.equals("cat")) {
                                 obj.setInt("type_tmp", 2);
                             } else if (oldType.equals("hamster")) {
                                 obj.setInt("type_tmp", 3);
                             }
                         }
                     })
                     .removeField("type")
                     .renameField("type_tmp", "type");
             oldVersion++;
         }
     }

     public int hashCode() {
         return Migration.class.hashCode();
     }

     public boolean equals(Object object) {
         if(object == null) {
             return false;
         }
         return object instanceof Migration;
     }
 }