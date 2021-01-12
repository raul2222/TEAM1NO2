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
public class es_upv_inodos_data_SystemItemRealmProxy extends es.upv.inodos.data.SystemItem
    implements RealmObjectProxy, es_upv_inodos_data_SystemItemRealmProxyInterface {

    static final class SystemItemColumnInfo extends ColumnInfo {
        long idColKey;
        long eventColKey;
        long timestampColKey;

        SystemItemColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("SystemItem");
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.eventColKey = addColumnDetails("event", "event", objectSchemaInfo);
            this.timestampColKey = addColumnDetails("timestamp", "timestamp", objectSchemaInfo);
        }

        SystemItemColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new SystemItemColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final SystemItemColumnInfo src = (SystemItemColumnInfo) rawSrc;
            final SystemItemColumnInfo dst = (SystemItemColumnInfo) rawDst;
            dst.idColKey = src.idColKey;
            dst.eventColKey = src.eventColKey;
            dst.timestampColKey = src.timestampColKey;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private SystemItemColumnInfo columnInfo;
    private ProxyState<es.upv.inodos.data.SystemItem> proxyState;

    es_upv_inodos_data_SystemItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (SystemItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<es.upv.inodos.data.SystemItem>(this);
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
    public String realmGet$event() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.eventColKey);
    }

    @Override
    public void realmSet$event(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.eventColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.eventColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.eventColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.eventColKey, value);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("SystemItem", false, 3, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("event", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("timestamp", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static SystemItemColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new SystemItemColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "SystemItem";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "SystemItem";
    }

    @SuppressWarnings("cast")
    public static es.upv.inodos.data.SystemItem createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        es.upv.inodos.data.SystemItem obj = null;
        if (update) {
            Table table = realm.getTable(es.upv.inodos.data.SystemItem.class);
            SystemItemColumnInfo columnInfo = (SystemItemColumnInfo) realm.getSchema().getColumnInfo(es.upv.inodos.data.SystemItem.class);
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
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(es.upv.inodos.data.SystemItem.class), false, Collections.<String> emptyList());
                    obj = new io.realm.es_upv_inodos_data_SystemItemRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.es_upv_inodos_data_SystemItemRealmProxy) realm.createObjectInternal(es.upv.inodos.data.SystemItem.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.es_upv_inodos_data_SystemItemRealmProxy) realm.createObjectInternal(es.upv.inodos.data.SystemItem.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final es_upv_inodos_data_SystemItemRealmProxyInterface objProxy = (es_upv_inodos_data_SystemItemRealmProxyInterface) obj;
        if (json.has("event")) {
            if (json.isNull("event")) {
                objProxy.realmSet$event(null);
            } else {
                objProxy.realmSet$event((String) json.getString("event"));
            }
        }
        if (json.has("timestamp")) {
            if (json.isNull("timestamp")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
            } else {
                objProxy.realmSet$timestamp((long) json.getLong("timestamp"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static es.upv.inodos.data.SystemItem createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final es.upv.inodos.data.SystemItem obj = new es.upv.inodos.data.SystemItem();
        final es_upv_inodos_data_SystemItemRealmProxyInterface objProxy = (es_upv_inodos_data_SystemItemRealmProxyInterface) obj;
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
            } else if (name.equals("event")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$event((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$event(null);
                }
            } else if (name.equals("timestamp")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$timestamp((long) reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
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

    static es_upv_inodos_data_SystemItemRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(es.upv.inodos.data.SystemItem.class), false, Collections.<String>emptyList());
        io.realm.es_upv_inodos_data_SystemItemRealmProxy obj = new io.realm.es_upv_inodos_data_SystemItemRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static es.upv.inodos.data.SystemItem copyOrUpdate(Realm realm, SystemItemColumnInfo columnInfo, es.upv.inodos.data.SystemItem object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (es.upv.inodos.data.SystemItem) cachedRealmObject;
        }

        es.upv.inodos.data.SystemItem realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(es.upv.inodos.data.SystemItem.class);
            long pkColumnKey = columnInfo.idColKey;
            String value = ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$id();
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
                    realmObject = new io.realm.es_upv_inodos_data_SystemItemRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static es.upv.inodos.data.SystemItem copy(Realm realm, SystemItemColumnInfo columnInfo, es.upv.inodos.data.SystemItem newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (es.upv.inodos.data.SystemItem) cachedRealmObject;
        }

        es_upv_inodos_data_SystemItemRealmProxyInterface unmanagedSource = (es_upv_inodos_data_SystemItemRealmProxyInterface) newObject;

        Table table = realm.getTable(es.upv.inodos.data.SystemItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idColKey, unmanagedSource.realmGet$id());
        builder.addString(columnInfo.eventColKey, unmanagedSource.realmGet$event());
        builder.addInteger(columnInfo.timestampColKey, unmanagedSource.realmGet$timestamp());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.es_upv_inodos_data_SystemItemRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, es.upv.inodos.data.SystemItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(es.upv.inodos.data.SystemItem.class);
        long tableNativePtr = table.getNativePtr();
        SystemItemColumnInfo columnInfo = (SystemItemColumnInfo) realm.getSchema().getColumnInfo(es.upv.inodos.data.SystemItem.class);
        long pkColumnKey = columnInfo.idColKey;
        String primaryKeyValue = ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$id();
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
        String realmGet$event = ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$event();
        if (realmGet$event != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.eventColKey, objKey, realmGet$event, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampColKey, objKey, ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$timestamp(), false);
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(es.upv.inodos.data.SystemItem.class);
        long tableNativePtr = table.getNativePtr();
        SystemItemColumnInfo columnInfo = (SystemItemColumnInfo) realm.getSchema().getColumnInfo(es.upv.inodos.data.SystemItem.class);
        long pkColumnKey = columnInfo.idColKey;
        es.upv.inodos.data.SystemItem object = null;
        while (objects.hasNext()) {
            object = (es.upv.inodos.data.SystemItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            String primaryKeyValue = ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$id();
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
            String realmGet$event = ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$event();
            if (realmGet$event != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.eventColKey, objKey, realmGet$event, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.timestampColKey, objKey, ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$timestamp(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, es.upv.inodos.data.SystemItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(es.upv.inodos.data.SystemItem.class);
        long tableNativePtr = table.getNativePtr();
        SystemItemColumnInfo columnInfo = (SystemItemColumnInfo) realm.getSchema().getColumnInfo(es.upv.inodos.data.SystemItem.class);
        long pkColumnKey = columnInfo.idColKey;
        String primaryKeyValue = ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$id();
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
        String realmGet$event = ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$event();
        if (realmGet$event != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.eventColKey, objKey, realmGet$event, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.eventColKey, objKey, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampColKey, objKey, ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$timestamp(), false);
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(es.upv.inodos.data.SystemItem.class);
        long tableNativePtr = table.getNativePtr();
        SystemItemColumnInfo columnInfo = (SystemItemColumnInfo) realm.getSchema().getColumnInfo(es.upv.inodos.data.SystemItem.class);
        long pkColumnKey = columnInfo.idColKey;
        es.upv.inodos.data.SystemItem object = null;
        while (objects.hasNext()) {
            object = (es.upv.inodos.data.SystemItem) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            String primaryKeyValue = ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$id();
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
            String realmGet$event = ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$event();
            if (realmGet$event != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.eventColKey, objKey, realmGet$event, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.eventColKey, objKey, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.timestampColKey, objKey, ((es_upv_inodos_data_SystemItemRealmProxyInterface) object).realmGet$timestamp(), false);
        }
    }

    public static es.upv.inodos.data.SystemItem createDetachedCopy(es.upv.inodos.data.SystemItem realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        es.upv.inodos.data.SystemItem unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new es.upv.inodos.data.SystemItem();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (es.upv.inodos.data.SystemItem) cachedObject.object;
            }
            unmanagedObject = (es.upv.inodos.data.SystemItem) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        es_upv_inodos_data_SystemItemRealmProxyInterface unmanagedCopy = (es_upv_inodos_data_SystemItemRealmProxyInterface) unmanagedObject;
        es_upv_inodos_data_SystemItemRealmProxyInterface realmSource = (es_upv_inodos_data_SystemItemRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$event(realmSource.realmGet$event());
        unmanagedCopy.realmSet$timestamp(realmSource.realmGet$timestamp());

        return unmanagedObject;
    }

    static es.upv.inodos.data.SystemItem update(Realm realm, SystemItemColumnInfo columnInfo, es.upv.inodos.data.SystemItem realmObject, es.upv.inodos.data.SystemItem newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        es_upv_inodos_data_SystemItemRealmProxyInterface realmObjectTarget = (es_upv_inodos_data_SystemItemRealmProxyInterface) realmObject;
        es_upv_inodos_data_SystemItemRealmProxyInterface realmObjectSource = (es_upv_inodos_data_SystemItemRealmProxyInterface) newObject;
        Table table = realm.getTable(es.upv.inodos.data.SystemItem.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addString(columnInfo.idColKey, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.eventColKey, realmObjectSource.realmGet$event());
        builder.addInteger(columnInfo.timestampColKey, realmObjectSource.realmGet$timestamp());

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("SystemItem = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{event:");
        stringBuilder.append(realmGet$event() != null ? realmGet$event() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp());
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
        es_upv_inodos_data_SystemItemRealmProxy aSystemItem = (es_upv_inodos_data_SystemItemRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aSystemItem.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSystemItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aSystemItem.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
