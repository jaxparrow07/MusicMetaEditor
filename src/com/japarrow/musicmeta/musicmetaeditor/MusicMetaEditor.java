package com.japarrow.musicmeta.musicmetaeditor;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.YailList;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;

import android.net.Uri;
import android.provider.MediaStore;

import com.mpatric.mp3agic.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicMetaEditor extends AndroidNonvisibleComponent {

  private String audioPath = null;
  private Mp3File audioFile = null;
  private ID3v2 audioTags = null;
  private boolean isFileLoaded = false;

  private Uri mAlbumArtUri = null;

  private Context mContext;
  private Activity mActivity;
  private ComponentContainer mContainer;

  public MusicMetaEditor(ComponentContainer container) {

    super(container.$form());
    this.mContainer = container;
    this.mContext = container.$context();
    this.mActivity = (Activity) mContext;

  }

  private void scanMedia(String path) {
      File file = new File(path);
      Uri uri = Uri.fromFile(file);
      Intent scanFileIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
      mContext.sendBroadcast(scanFileIntent);
  }

  @SimpleFunction(description = "")
  public void UnLoadAudio() {

    if (isFileLoaded) {
      this.audioPath = null;
      this.audioFile = null;
      this.audioTags = null;
      this.isFileLoaded = false;
      if (this.mAlbumArtUri != null) {
          mActivity.getContentResolver().delete(this.mAlbumArtUri, null, null);
      }
    }

  }


  @SimpleFunction(description = "")
  public void LoadAudio(String filePath) {

    this.audioPath = filePath;

    try {

        this.audioFile = new Mp3File(filePath);

    } catch (Exception e) {

        LoadFailed(filePath);
        throw new YailRuntimeError(e.getMessage(), "MusicMetaEditor.LoadFailed");
      
    }

    if (audioFile.hasId3v2Tag()) {

      audioTags = audioFile.getId3v2Tag();
      Loaded(filePath, true);


    } else if (audioFile.hasId3v1Tag()) {

      ID3v1 v1Tag = audioFile.getId3v1Tag();

      audioTags = new ID3v24Tag();

      audioFile.setId3v2Tag(audioTags);

      // Updating from v1 to v24
      audioTags.setTitle(v1Tag.getTitle());
      audioTags.setArtist(v1Tag.getArtist());
      audioTags.setAlbum(v1Tag.getAlbum());
      audioTags.setComment(v1Tag.getComment());
      audioTags.setYear(v1Tag.getYear());
      audioTags.setTrack(v1Tag.getTrack());
      audioTags.setGenre(v1Tag.getGenre());

      Loaded(filePath, false);

    }

    else {
      audioTags = new ID3v24Tag();

      audioFile.setId3v2Tag(audioTags);
      Loaded(filePath, true);

    }

    this.isFileLoaded = true;

  }

  @SimpleFunction(description = "")
  public void SaveAs(String filePath) {

    if (isFileLoaded && this.audioTags != null) {

      try {
        audioFile.save(filePath);
        scanMedia(filePath);

      } catch (Exception e) {
        SaveFailed(filePath);
        throw new YailRuntimeError(e.getMessage(), "MusicMetaEditor.SaveFailed");

      } finally {
        Saved(filePath);

      }


    }

  }

  // Events
  @SimpleEvent(description = "")
  public void Loaded(String filePath, boolean isV2){
     EventDispatcher.dispatchEvent(this,"Loaded", filePath, isV2);
  }
  @SimpleEvent(description = "")
  public void LoadFailed(String filePath){
     EventDispatcher.dispatchEvent(this,"LoadFailed", filePath);
  }

  @SimpleEvent(description = "")
  public void Saved(String filePath){
     EventDispatcher.dispatchEvent(this,"Saved", filePath);
  }
  @SimpleEvent(description = "")
  public void SaveFailed(String filePath){
     EventDispatcher.dispatchEvent(this,"SaveFailed", filePath);
  }

  // Properties
  @SimpleProperty(description = "")
  public String Title() {
    return (audioTags.getTitle() != null) ? audioTags.getTitle() : "" ;
  }
  @SimpleProperty(description = "")
  public void Title(String str) {
    audioTags.setTitle(str);
  }

  @SimpleProperty(description = "")
  public String Album() {
    return (audioTags.getAlbum() != null) ? audioTags.getAlbum() : "" ;
  }
  @SimpleProperty(description = "")
  public void Album(String str) {
    audioTags.setAlbum(str);
  }

  @SimpleProperty(description = "")
  public String Artist() {
    return (audioTags.getArtist() != null) ? audioTags.getArtist() : "" ;
  }
  @SimpleProperty(description = "")
  public void Artist(String str) {
    audioTags.setArtist(str);
  }

  @SimpleProperty(description = "")
  public String Track() {
    return (audioTags.getTrack() != null) ? audioTags.getTrack() : "" ;
  }
  @SimpleProperty(description = "")
  public void Track(String str) {
    audioTags.setTrack(str);
  }

  @SimpleProperty(description = "")
  public String Version() {
    return (audioTags.getVersion() != null) ? audioTags.getVersion() : "" ;
  }

  @SimpleProperty(description = "")
  public int Genre() {
    return audioTags.getGenre();
  }
  @SimpleProperty(description = "")
  public void Genre(int val) {
    audioTags.setGenre(val);
  }

  @SimpleProperty(description = "")
  public String GenreDescription() {
    return (audioTags.getGenreDescription() != null) ? audioTags.getGenreDescription() : "" ;
  }
  @SimpleProperty(description = "")
  public void GenreDescription(String str) {
    audioTags.setGenreDescription(str);
  }

  @SimpleProperty(description = "")
  public String Composer() {
    return (audioTags.getComposer() != null) ? audioTags.getComposer() : "" ;
  }
  @SimpleProperty(description = "")
  public void Composer(String str) {
    audioTags.setComposer(str);
  }

  @SimpleProperty(description = "")
  public int BPM() {
    return audioTags.getBPM();
  }
  @SimpleProperty(description = "")
  public void BPM(int val) {
    audioTags.setBPM(val);
  }

  @SimpleProperty(description = "")
  public String AlbumArtist() {
    return (audioTags.getAlbumArtist() != null) ? audioTags.getAlbumArtist() : "" ;
  }
  @SimpleProperty(description = "")
  public void AlbumArtist(String str) {
    audioTags.setAlbumArtist(str);
  }


  @SimpleProperty(description = "")
  public String ArtistUrl() {
    return (audioTags.getArtistUrl() != null) ? audioTags.getArtistUrl() : "" ;
  }
  @SimpleProperty(description = "")
  public void ArtistUrl(String str) {
    audioTags.setArtistUrl(str);
  }

  @SimpleProperty(description = "")
  public String AudioSourceUrl() {
    return (audioTags.getAudioSourceUrl() != null) ? audioTags.getAudioSourceUrl() : "" ;
  }
  @SimpleProperty(description = "")
  public void AudioSourceUrl(String str) {
    audioTags.setAudioSourceUrl(str);
  }

  @SimpleProperty(description = "")
  public String AudioFileUrl() {
    return (audioTags.getAudiofileUrl() != null) ? audioTags.getAudiofileUrl() : "" ;
  }
  @SimpleProperty(description = "")
  public void AudioFileUrl(String str) {
    audioTags.setAudiofileUrl(str);
  }

  @SimpleProperty(description = "")
  public String CommercialUrl() {
    return (audioTags.getCommercialUrl() != null) ? audioTags.getCommercialUrl() : "" ;
  }
  @SimpleProperty(description = "")
  public void CommercialUrl(String str) {
    audioTags.setCommercialUrl(str);
  }

  @SimpleProperty(description = "")
  public String Copyright() {
    return (audioTags.getCopyright() != null) ? audioTags.getCopyright() : "" ;
  }
  @SimpleProperty(description = "")
  public void Copyright(String str) {
    audioTags.setCopyright(str);
  }

  @SimpleProperty(description = "")
  public String CopyrightUrl() {
    return (audioTags.getCopyrightUrl() != null) ? audioTags.getCopyrightUrl() : "" ;
  }
  @SimpleProperty(description = "")
  public void CopyrightUrl(String str) {
    audioTags.setCopyrightUrl(str);
  }

  @SimpleProperty(description = "")
  public String Date() {
    return (audioTags.getDate() != null) ? audioTags.getDate() : "" ;
  }
  @SimpleProperty(description = "")
  public void Date(String str) {
    audioTags.setDate(str);
  }

  @SimpleProperty(description = "")
  public String Year() {
    return (audioTags.getYear() != null) ? audioTags.getYear() : "" ;
  }
  @SimpleProperty(description = "")
  public void Year(String str) {
    audioTags.setYear(str);
  }

  @SimpleProperty(description = "")
  public String Encoder() {
    return (audioTags.getEncoder() != null) ? audioTags.getEncoder() : "" ;
  }
  @SimpleProperty(description = "")
  public void Encoder(String str) {
    audioTags.setEncoder(str);
  }

  @SimpleProperty(description = "")
  public String Grouping() {
    return (audioTags.getGrouping() != null) ? audioTags.getGrouping() : "" ;
  }
  @SimpleProperty(description = "")
  public void Grouping(String str) {
    audioTags.setGrouping(str);
  }

  @SimpleProperty(description = "")
  public String ItunesComment() {
    return (audioTags.getItunesComment() != null) ? audioTags.getItunesComment() : "" ;
  }
  @SimpleProperty(description = "")
  public void ItunesComment(String str) {
    audioTags.setItunesComment(str);
  }

  @SimpleProperty(description = "")
  public String Key() {
    return (audioTags.getKey() != null) ? audioTags.getKey() : "" ;
  }
  @SimpleProperty(description = "")
  public void Key(String str) {
    audioTags.setKey(str);
  }

  @SimpleProperty(description = "")
  public int Length() {
    return audioTags.getLength();
  }

  @SimpleProperty(description = "")
  public String Lyrics() {
    return (audioTags.getLyrics() != null) ? audioTags.getLyrics() : "" ;
  }
  @SimpleProperty(description = "")
  public void Lyics(String str) {
    audioTags.setLyrics(str);
  }

  @SimpleProperty(description = "")
  public String OriginalArtist() {
    return (audioTags.getOriginalArtist() != null) ? audioTags.getOriginalArtist() : "" ;
  }
  @SimpleProperty(description = "")
  public void OriginalArtist(String str) {
    audioTags.setOriginalArtist(str);
  }

  @SimpleProperty(description = "")
  public boolean hasPadding() {
    return audioTags.getPadding();
  }
  @SimpleProperty(description = "")
  public void hasPadding(boolean bol) {
    audioTags.setPadding(bol);
  }

  @SimpleProperty(description = "")
  public boolean hasFooter() {
    return audioTags.hasFooter();
  }
  @SimpleProperty(description = "")
  public void hasFooter(boolean bol) {
    audioTags.setFooter(bol);
  }

  @SimpleProperty(description = "")
  public boolean hasUnsynchronisation() {
    return audioTags.hasUnsynchronisation();
  }
  @SimpleProperty(description = "")
  public void hasUnsynchronisation(boolean bol) {
    audioTags.setUnsynchronisation(bol);
  }

  @SimpleProperty(description = "")
  public boolean isCompilation() {
    return audioTags.isCompilation();
  }
  @SimpleProperty(description = "")
  public void isCompilation(boolean bol) {
    audioTags.setCompilation(bol);
  }

  @SimpleProperty(description = "")
  public boolean isObseleteFormat() {
    return audioTags.getObseleteFormat();
  }

  @SimpleProperty(description = "")
  public String PartOfSet() {
    return (audioTags.getPartOfSet() != null) ? audioTags.getPartOfSet() : "" ;
  }
  @SimpleProperty(description = "")
  public void PartOfSet(String str) {
    audioTags.setPartOfSet(str);
  }

  @SimpleProperty(description = "")
  public String PaymentUrl() {
    return (audioTags.getPaymentUrl() != null) ? audioTags.getPaymentUrl() : "" ;
  }
  @SimpleProperty(description = "")
  public void PaymentUrl(String str) {
    audioTags.setPaymentUrl(str);
  }

  @SimpleProperty(description = "")
  public String Publisher() {
    return (audioTags.getPublisher() != null) ? audioTags.getPublisher() : "" ;
  }
  @SimpleProperty(description = "")
  public void Publisher(String str) {
    audioTags.setPublisher(str);
  }

  @SimpleProperty(description = "")
  public String PublisherUrl() {
    return (audioTags.getPublisherUrl() != null) ? audioTags.getPublisherUrl() : "" ;
  }
  @SimpleProperty(description = "")
  public void PublisherUrl(String str) {
    audioTags.setPublisherUrl(str);
  }

  @SimpleProperty(description = "")
  public String RadioStationUrl() {
    return (audioTags.getRadiostationUrl() != null) ? audioTags.getRadiostationUrl() : "" ;
  }
  @SimpleProperty(description = "")
  public void RadioStationUrl(String str) {
    audioTags.setRadiostationUrl(str);
  }

  @SimpleProperty(description = "")
  public String Url() {
    return (audioTags.getUrl() != null) ? audioTags.getUrl() : "" ;
  }
  @SimpleProperty(description = "")
  public void Url(String str) {
    audioTags.setUrl(str);
  }

  @SimpleProperty(description = "")
  public int WmpRating() {
    return audioTags.getWmpRating();
  }
  @SimpleProperty(description = "")
  public void WmpRating(int val) {
    audioTags.setWmpRating(val);
  }

  @SimpleProperty(description = "")
  public String AlbumArt() {

    byte[] bmpBytes = audioTags.getAlbumImage();
    BitmapFactory.Options options = new BitmapFactory.Options();

    options.inMutable = true;

    if (bmpBytes != null) {
      Bitmap bmp = BitmapFactory.decodeByteArray(bmpBytes, 0, bmpBytes.length, options);

      // Returns path from Bitmap object
      String path = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bmp, "Temp-album", null);
      mAlbumArtUri = Uri.parse(path);

      return path;

    } else {
      return "";
    }

  }
  @SimpleProperty(description = "")
  public void AlbumArt(String str) {

    if (this.mAlbumArtUri != null) {
        mActivity.getContentResolver().delete(this.mAlbumArtUri, null, null);
    }

    try {

      InputStream inStream = new FileInputStream(str);
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();

      Bitmap bitmap = BitmapFactory.decodeStream(inStream);
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);

      audioTags.setAlbumImage(outStream.toByteArray(), "image/jpeg");

    } catch(Exception e) {

      throw new YailRuntimeError(e.getMessage(), "MusicMetaEditor.AlbumArt");

    }

  }

}
