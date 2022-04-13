# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.japarrow.musicmeta.musicmetaeditor.MusicMetaEditor {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/japarrow/musicmeta/musicmetaeditor/repack'
-flattenpackagehierarchy
-dontpreverify
