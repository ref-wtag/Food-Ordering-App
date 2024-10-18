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
         RealmSchema schema = realm.getSchema();
         if (oldVersion == 0) {
             RealmObjectSchema personSchema = schema.get("Person");
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
         if (oldVersion == 1) {

             RealmObjectSchema petSchema = schema.create("Pet")
                     .addField("name", String.class, FieldAttribute.REQUIRED)
                     .addField("type", String.class, FieldAttribute.REQUIRED);

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

         if (oldVersion == 2) {
             RealmObjectSchema personSchema = schema.get("Person");
             personSchema.setNullable("fullName", true); // fullName is nullable now.

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