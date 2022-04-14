<div align="center">
<h1 text-align="center" align="center" style="text-align:center"><kbd>🎵</kbd> Music Meta Editor Extension</h1>An ID3 Tag editor extension for app inventor and it's clones.
</div>
<hr>

This extension is based on this library (**mp3agic**) : https://github.com/mpatric/mp3agic

All the credits goes to the developer of this particular library. This library made life easier to change the get the tags.

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

*TO-DO : Notes for all TAGS* 

| Tag ( Property )    | Type | Notes ( if any ) |
|--------------------------|-------------------|-----------------|
| **Title**   | String
| **Album** | String
| **Artist** | String
| **Artist Url** | String |
| **Track** | String
| **Genre** | int | Refer to [Genre list](https://en.wikipedia.org/wiki/ID3#Genre_list_in_ID3v1[12])
| **Genre Description** | String
| **Composer** | String
| **BPM** | int | **B**eats **P**er **M**inute ( tempo/speed )
| **Album Art** | String  |Path to the image file ( if needed to change ) |
| **Album Artist** | String |
| **Audio Source Url** | String |
| **Audio File Url** | String |
| **Commercial Url** | String |
| **Copyright** | String |
| **Date** | String |
| **Year** | String |
| **Encoder** | String |
| **Grouping** | String |
| **iTunes Comment** | String |
| **Key** | String |
| **Length** | int  |
| **Lyrics** | String |
| **Original Artist** | String |
| **Part Of Set** | String |
| **Payment Url** | String |
| **Publisher** | String |
| **Publisher Url** | String |
| **Radio Station Url** | String |
| **Url** | String |
| **Wmp Rating** | String |
| **has Padding** | bool |
| **has Footer** | bool |
| **has Unsynchronisation** | bool |
| **is Compilation** | bool | 
| **is Obselete Format ( read-only )** | bool |
| **Version ( read-only )** | String |



## `📂` Downloads

Extension  : Check ![Releases]()

## `ℹ️` Credits

The library which this extension is based on :
https://github.com/mpatric/mp3agic

Built With [Rush](https://github.com/ShreyashSaitwal/rush-cli).
