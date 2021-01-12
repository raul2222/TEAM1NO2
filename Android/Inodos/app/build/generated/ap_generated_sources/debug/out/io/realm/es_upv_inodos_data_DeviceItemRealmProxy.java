package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class es_upv_inodos_data_DeviceItemRealmProxy extends es.upv.inodos.data.DeviceItem
    implements RealmObjectProxy, es_upv_inodos_data_DeviceItemRealmProxyInterface {

    static final class DeviceItemColumnInfo extends ColumnInfo {
        long idColKey;
        long nameColKey;
        long macAddressColKey;
        long timestampColKey;
        long latitudeColKey;
        long longitudeColKey;

        DeviceItemColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("DeviceItem");
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.nameColKey = addColumnDetails("name", "name", objectSchemaInfo);
            this.macAddressColKey = addColumnDetails("macAddress", "macAddress", objectSchemaInfo);
            this.timestampColKey = addColumnDetails("timestamp", "timestamp", objectSchemaInfo);
            this.latitudeColKey = addColumnDetails("latitude", "latitude", objectSchemaInfo);
            this.longitudeColKey = addColumnDetails("longitude", "longitude", objectSchemaInfo);
        }

        DeviceItemColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new DeviceItemColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final DeviceItemColumnInfo src = (DeviceItemColumnInfo) rawSrc;
            final DeviceItemColumnInfo dst = (DeviceItemColumnInfo) rawDst;
            dst.idColKey = src.idColKey;
            dst.nameColKey = src.nameColKey;
            dst.macAddressColKey = src.macAddressColKey;
            dst.timestampColKey = src.timestampColKey;
            dst.latitudeColKey = src.latitudeColKey;
            dst.longitudeColKey = src.longitudeColKey;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private DeviceItemColumnInfo columnInfo;
    private ProxyState<es.upv.inodos.data.DeviceItem> proxyState;

    es_upv_inodos_data_DeviceItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (DeviceItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<es.upv.inodos.data.DeviceItem>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idColKey);
    }

    @Override
    public void realmSet$id(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameColKey);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$macAddress() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.macAddressColKey);
    }

    @Override
    public void realmSet$macAddress(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.macAddressColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.macAddressColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.macAddressColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.macAddressColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public long realmGet$timestamp() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.timestampColKey);
    }

    @Override
    public void realmSet$timestamp(long value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.timestampColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.timestampColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$latitude() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.latitudeColKey);
    }

    @Override
    public void realmSet$latitude(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.latitudeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.latitudeColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$longitude() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.longitudeColKey);
    }

    @Override
    public void realmSet$longitude(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.longitudeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.longitudeColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("DeviceItem", false, 6, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("macAddress", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("timestamp", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("latitude", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("longitude", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static DeviceItemColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new DeviceItemColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "DeviceItem";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "DeviceItem";
    }

    @SuppressWarnings("cast")
    public static es.upv.inodos.data.DeviceItem createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        es.upv.inodos.data.DeviceItem obj = null;
        if (update) {
            Table table = realm.getTable(es.upv.inodos.data.DeviceItem.class);
            DeviceItemColumnInfo columnInfo = (DeviceItemColumnInfo) realm.getSchema().getColumnInfo(es.upv.inodos.data.DeviceItem.class);
            long pkColumnKey = columnInfo.idColKey;
            long objKey = Table.NO_MATCH;
            if (json.isNull("id")) {
                objKey = table.findFirstNull(pkColumnKey);
            } else {
                objKey = table.findFirstString(pkColumnKey, json.getString("id"));
            }
            if (objKey != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(es.upv.inodos.data.DeviceItem.class), false, Collections.<String> emptyList());
                    obj = new io.realm.es_upv_inodos_data_DeviceItemRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.es_upv_inodos_data_DeviceItemRealmProxy) realm.createObjectInternal(es.upv.inodos.data.DeviceItem.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.es_upv_inodos_data_DeviceItemRealmProxy) realm.createObjectInternal(es.upv.inodos.data.DeviceItem.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final es_upv_inodos_data_DeviceItemRealmProxyInterface objProxy = (es_upv_inodos_data_DeviceItemRealmProxyInterface) obj;
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("macAddress")) {
            if (json.isNull("macAddress")) {
                objProxy.realmSet$macAddress(null);
            } else {
                objProxy.realmSet$macAddress((String) json.getString("macAddress"));
            }
        }
        if (json.has("timestamp")) {
            if (json.isNull("timestamp")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
            } else {
                objProxy.realmSet$timestamp((long) json.getLong("timestamp"));
            }
        }
        if (json.has("latitude")) {
            if (json.isNull("latitude")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'latitude' to null.");
            } else {
                objProxy.realmSet$latitude((double) json.getDouble("latitude"));
            }
        }
        if (json.has("longitude")) {
            if (json.isNull("longitude")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'longitude' to null.");
            } else {
                objProxy.realmSet$longitude((double) json.getDouble("longitude"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static es.upv.inodos.data.DeviceItem createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final es.upv.inodos.data.DeviceItem obj = new es.upv.inodos.data.DeviceItem();
        final es_upv_inodos_data_DeviceItemRealmProxyInterface objProxy = (es_upv_inodos_data_DeviceItemRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("macAddress")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$macAddress((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$macAddress(null);
                }
            } else if (name.equals("timestamp")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$timestamp((long) reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
                }
            } else if (name.equals("latitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$latitude((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'latitude' to null.");
                }
            } else if (name.equals("longitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$longitude((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'longitude' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        return realm.copyToRealm(obj);
    }

    static es_upv_inodos_data_DeviceItemRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(es.upv.inodos.data.DeviceItem.class), false, Collections.<String>emptyList());
        io.realm.es_upv_inodos_data_DeviceItemRealmProxy obj = new io.realm.es_upv_inodos_data_DeviceItemRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static es.upv.inodos.data.DeviceItem copyOrUpdate(Realm realm, DeviceItemColumnInfo columnInfo, es.upv.inodos.data.DeviceItem object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (es.upv.inodos.data.DeviceItem) cachedRealmObject;
        }

        es.upv.inodos.data.DeviceItem realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(es.upv.inodos.data.DeviceItem.class);
            long pkColumnKey = columnInfo.idColKey;
            String value = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$id();
            long objKey = Table.NO_MATCH;
            if (value == null) {
                objKey = table.findFirstNull(pkColumnKey);
            } else {
                objKey = table.findFirstString(pkColumnKey, value);
            }
            if (objKey == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.es_upv_inodos_data_DeviceItemRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static es.upv.inodos.data.DeviceItem copy(Realm realm, DeviceItemColumnInfo columnInfo, es.upv.inodos.data.DeviceItem newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (es.upv.inodos.data.DeviceItem) cachedRealmObject;
        }

        es_upv_inodos_data_DeviceItemRealmProxyInterface unmanagedSource = (es_upv_inodos_data_DeviceItemRealmProxyInterface) newObject;

        Table table = realm.getTable(es.upv.inodos.data.DeviceItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idColKey, unmanagedSource.realmGet$id());
        builder.addString(columnInfo.nameColKey, unmanagedSource.realmGet$name());
        builder.addString(columnInfo.macAddressColKey, unmanagedSource.realmGet$macAddress());
        builder.addInteger(columnInfo.timestampColKey, unmanagedSource.realmGet$timestamp());
        builder.addDouble(columnInfo.latitudeColKey, unmanagedSource.realmGet$latitude());
        builder.addDouble(columnInfo.longitudeColKey, unmanagedSource.realmGet$longitude());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.es_upv_inodos_data_DeviceItemRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, es.upv.inodos.data.DeviceItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(es.upv.inodos.data.DeviceItem.class);
        long tableNativePtr = table.getNativePtr();
        DeviceItemColumnInfo columnInfo = (DeviceItemColumnInfo) realm.getSchema().getColumnInfo(es.upv.inodos.data.DeviceItem.class);
        long pkColumnKey = columnInfo.idColKey;
        String primaryKeyValue = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$id();
        long objKey = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            objKey = Table.nativeFindFirstNull(tableNativePtr, pkColumnKey);
        } else {
            objKey = Table.nativeFindFirstString(tableNativePtr, pkColumnKey, primaryKeyValue);
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, objKey);
        String realmGet$name = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameColKey, objKey, realmGet$name, false);
        }
        String realmGet$macAddress = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$macAddress();
        if (realmGet$macAddress != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.macAddressColKey, objKey, realmGet$macAddress, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$timestamp(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$latitude(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$longitude(), false);
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(es.upv.inodos.data.DeviceItem.class);
        long tableNativePtr = table.getNativePtr();
        DeviceItemColumnInfo columnInfo = (DeviceItemColumnInfo) realm.getSchema().getColumnInfo(es.upv.inodos.data.DeviceItem.class);
        long pkColumnKey = columnInfo.idColKey;
        es.upv.inodos.data.DeviceItem object = null;
        while (objects.hasNext()) {
            object = (es.upv.inodos.data.DeviceItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            String primaryKeyValue = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$id();
            long objKey = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                objKey = Table.nativeFindFirstNull(tableNativePtr, pkColumnKey);
            } else {
                objKey = Table.nativeFindFirstString(tableNativePtr, pkColumnKey, primaryKeyValue);
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, objKey);
            String realmGet$name = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameColKey, objKey, realmGet$name, false);
            }
            String realmGet$macAddress = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$macAddress();
            if (realmGet$macAddress != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.macAddressColKey, objKey, realmGet$macAddress, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.timestampColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$timestamp(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$latitude(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$longitude(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, es.upv.inodos.data.DeviceItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(es.upv.inodos.data.DeviceItem.class);
        long tableNativePtr = table.getNativePtr();
        DeviceItemColumnInfo columnInfo = (DeviceItemColumnInfo) realm.getSchema().getColumnInfo(es.upv.inodos.data.DeviceItem.class);
        long pkColumnKey = columnInfo.idColKey;
        String primaryKeyValue = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$id();
        long objKey = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            objKey = Table.nativeFindFirstNull(tableNativePtr, pkColumnKey);
        } else {
            objKey = Table.nativeFindFirstString(tableNativePtr, pkColumnKey, primaryKeyValue);
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, primaryKeyValue);
        }
        cache.put(object, objKey);
        String realmGet$name = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameColKey, objKey, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameColKey, objKey, false);
        }
        String realmGet$macAddress = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$macAddress();
        if (realmGet$macAddress != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.macAddressColKey, objKey, realmGet$macAddress, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.macAddressColKey, objKey, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$timestamp(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$latitude(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$longitude(), false);
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(es.upv.inodos.data.DeviceItem.class);
        long tableNativePtr = table.getNativePtr();
        DeviceItemColumnInfo columnInfo = (DeviceItemColumnInfo) realm.getSchema().getColumnInfo(es.upv.inodos.data.DeviceItem.class);
        long pkColumnKey = columnInfo.idColKey;
        es.upv.inodos.data.DeviceItem object = null;
        while (objects.hasNext()) {
            object = (es.upv.inodos.data.DeviceItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            String primaryKeyValue = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$id();
            long objKey = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                objKey = Table.nativeFindFirstNull(tableNativePtr, pkColumnKey);
            } else {
                objKey = Table.nativeFindFirstString(tableNativePtr, pkColumnKey, primaryKeyValue);
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, primaryKeyValue);
            }
            cache.put(object, objKey);
            String realmGet$name = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameColKey, objKey, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameColKey, objKey, false);
            }
            String realmGet$macAddress = ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$macAddress();
            if (realmGet$macAddress != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.macAddressColKey, objKey, realmGet$macAddress, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.macAddressColKey, objKey, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.timestampColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$timestamp(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$latitude(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeColKey, objKey, ((es_upv_inodos_data_DeviceItemRealmProxyInterface) object).realmGet$longitude(), false);
        }
    }

    public static es.upv.inodos.data.DeviceItem createDetachedCopy(es.upv.inodos.data.DeviceItem realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        es.upv.inodos.data.DeviceItem unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new es.upv.inodos.data.DeviceItem();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (es.upv.inodos.data.DeviceItem) cachedObject.object;
            }
            unmanagedObject = (es.upv.inodos.data.DeviceItem) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        es_upv_inodos_data_DeviceItemRealmProxyInterface unmanagedCopy = (es_upv_inodos_data_DeviceItemRealmProxyInterface) unmanagedObject;
        es_upv_inodos_data_DeviceItemRealmProxyInterface realmSource = (es_upv_inodos_data_DeviceItemRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$macAddress(realmSource.realmGet$macAddress());
        unmanagedCopy.realmSet$timestamp(realmSource.realmGet$timestamp());
        unmanagedCopy.realmSet$latitude(realmSource.realmGet$latitude());
        unmanagedCopy.realmSet$longitude(realmSource.realmGet$longitude());

        return unmanagedObject;
    }

    static es.upv.inodos.data.DeviceItem update(Realm realm, DeviceItemColumnInfo columnInfo, es.upv.inodos.data.DeviceItem realmObject, es.upv.inodos.data.DeviceItem newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        es_upv_inodos_data_DeviceItemRealmProxyInterface realmObjectTarget = (es_upv_inodos_data_DeviceItemRealmProxyInterface) realmObject;
        es_upv_inodos_data_DeviceItemRealmProxyInterface realmObjectSource = (es_upv_inodos_data_DeviceItemRealmProxyInterface) newObject;
        Table table = realm.getTable(es.upv.inodos.data.DeviceItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addString(columnInfo.idColKey, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.nameColKey, realmObjectSource.realmGet$name());
        builder.addString(columnInfo.macAddressColKey, realmObjectSource.realmGet$macAddress());
        builder.addInteger(columnInfo.timestampColKey, realmObjectSource.realmGet$timestamp());
        builder.addDouble(columnInfo.latitudeColKey, realmObjectSource.realmGet$latitude());
        builder.addDouble(columnInfo.longitudeColKey, realmObjectSource.realmGet$longitude());

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DeviceItem = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{macAddress:");
        stringBuilder.append(realmGet$macAddress() != null ? realmGet$macAddress() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{latitude:");
        stringBuilder.append(realmGet$latitude());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{longitude:");
        stringBuilder.append(realmGet$longitude());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long objKey = proxyState.getRow$realm().getObjectKey();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (objKey ^ (objKey >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        es_upv_inodos_data_DeviceItemRealmProxy aDeviceItem = (es_upv_inodos_data_DeviceItemRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aDeviceItem.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDeviceItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aDeviceItem.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
