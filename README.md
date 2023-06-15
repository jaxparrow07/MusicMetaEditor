<div align="center">
<h1 text-align="center" align="center" style="text-align:center"><kbd>🎵</kbd> Music Meta Editor Extension</h1>An ID3 Tag editor extension for app inventor and it's clones.
</div>
<hr>

This extension is based on this library (**mp3agic**) : https://github.com/mpatric/mp3agic

All the credits goes to the developer of this particular library.

You can use this extension in your Music Player to allow the user to change the audio info of songs ( like changing album art, title, artist etc.,. ) like most recent music players do. The imagination is the limit.
<br>


<hr>

## `🗒️` Introduction to Id3

Wikipedia article ( for tag info ) : https://en.wikipedia.org/wiki/ID3

There are different version of Id3 such as ID3v1, ID3v2 ( v22,v23,v24 ). Id3v1 is too old school and there isn't much in that. So, this extension converts/copies your old ID3v1 tags to ID3v24 if it's the older version so that it ensures that you can use all the tags in your audio file. 
<hr>


## `🧩` Blocks
### Events
| Block | Description | Parameters |
|----------|------------------|------------------|
| ![component_event(6)\|273x84, 75%](https://kodular-community.s3.dualstack.eu-west-1.amazonaws.com/original/3X/4/8/48bdb7a48d60b247134c44e8228112bba7b72b3b.png) | Fired when the file is loaded | **filePath** ~ string<br>**isV2** ~ boolean |
|![component_event(3)\|296x84, 75%](https://kodular-community.s3.dualstack.eu-west-1.amazonaws.com/original/3X/0/4/048fb534394c4a376fe145a5732ef332710a992c.png) |Fired when the file has failed to load | **filePath** ~ string |
| ![component_event(5)\|263x84, 75%](https://kodular-community.s3.dualstack.eu-west-1.amazonaws.com/original/3X/2/1/215e3e8ac72d2e0a81c8b418cb9fd998798c960b.png)| Fired when the file is saved | **filePath** ~ string
|![component_event(4)\|295x84, 75%](https://kodular-community.s3.dualstack.eu-west-1.amazonaws.com/original/3X/7/2/720cca2496bc4cc15be0e0828f928d1907aa704c.png)| Fired when the file has failed to save | **filePath** ~ string |

### Functions
| Block | Description | Parameters |
|----------|------------------|------------------|
|![component_method(4)\|289x53, 100%](https://kodular-community.s3.dualstack.eu-west-1.amazonaws.com/original/3X/1/6/1621003fb7b1f7abd885cb568465e9654b4e3d3f.png) | Loads audio from **file Path** | **file Path** ~ string |
| ![component_method(5)\|300x29, 100%](https://kodular-community.s3.dualstack.eu-west-1.amazonaws.com/original/3X/1/4/14e555e749fb3130edbadd51d584a454114a083d.png) | Unloads an audio file ( if loaded )<br>*Note : You need to unload before loading a new one.* | N/A
| ![component_method(6)\|267x53, 100%](https://kodular-community.s3.dualstack.eu-west-1.amazonaws.com/original/3X/0/0/001bcc7c4884d256c3c21eae0f9fb7db7e8e1fc4.png) | Saves File as **file Path** <br> | **file Path** ~ string

### Properties ( Id3 Tags )
*Note : Album Art Accepts only **actual file path** and not **Uri** for now.*


| Tag ( Property )    | Type | Description/Notes |
|--------------------------|-------------------|-----------------|
| **Title**   | String | Song name |
| **Album** | String | Album name |
| **Artist** | String | Artist/Band name |
| **Artist Url** | String | Link to his website or socials |
| **Track** | String | Track number |
| **Genre** | int | ( Use **Genre Description** to read and write genre as "string" ) Refer to [Genre list](https://en.wikipedia.org/wiki/ID3#Genre_list_in_ID3v1[12])
| **Genre Description** | String | Genre in free string format
| **Composer** | String | Composer Name
| **BPM** | int | **B**eats **P**er **M**inute ( tempo/speed )
| **Album Art** | String  | Path to the image file ( if needed to change ) |
| **Album Artist** | String | Arist of the entire album |
| **Audio Source Url** | String | Source url of the audio |
| **Audio File Url** | String | File url of the audio |
| **Commercial Url** | String | Commercial url of the audio |
| **Copyright** | String | Copyright text |
| **Date** | String | N/A |
| **Year** | String | N/A |
| **Encoder** | String | Name of the encoding software/encoder |
| **Grouping** | String | Grouping with other songs ( songs that are linked to each other in some ways ) |
| **iTunes Comment** | String | Comments of iTunes ( music platform ) |
| **Key** | String | Key ( Tone ) of the song ( more info : https://en.wikipedia.org/wiki/Key_(music) )
| **Length** | int  | Length in milliseconds |
| **Lyrics** | String | Lyrics of the song }
| **Original Artist** | String | Name of the original artist ( don't know the diff. between artist and this )
| **Part Of Set** | String | Part of a set if any ( don't know which data should be here )
| **Payment Url** | String | Link to any payment page for the album/song |
| **Publisher** | String | Publisher name |
| **Publisher Url** | String | Any link to the publisher
| **Radio Station Url** | String | Any link to the radion station |
| **Url** | String | Link to this song |
| **Wmp Rating** | String | **W**indows **M**edia **P**layer rating |
| **has Padding** | bool | if the song has padding |
| **has Footer** | bool | if the song has footer |
| **has Unsynchronisation** | bool | if the song has unsynchronisation |
| **is Compilation** | bool | if the song is a compilation
| **is Obselete Format ( read-only )** | bool | if the file is obselete format |
| **Version ( read-only )** | String | Version of the metadata ( id3 ) |



## `📂` Downloads

Extension  : Check ![Releases](https://github.com/jaxparrow07/MusicMetaEditor/releases)

## `ℹ️` Credits

The library which this extension is based on :
https://github.com/mpatric/mp3agic

Built With [Rush](https://github.com/ShreyashSaitwal/rush-cli).
