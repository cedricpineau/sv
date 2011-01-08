(ns sv.util)

(defn static? [field]
  (java.lang.reflect.Modifier/isStatic
    (.getModifiers field)))

(defn get-record-field-names [record]
  (->> record
    .getDeclaredFields
    (remove static?)
    (map #(.getName %))
    (remove #{"__meta" "__extmap"})))

(defmacro empty-record [record]
  (let [klass (Class/forName (name record))
        field-count (count (get-record-field-names klass))]
    `(new ~klass ~@(repeat field-count nil))))
