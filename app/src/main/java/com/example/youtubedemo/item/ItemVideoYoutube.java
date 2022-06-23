package com.example.youtubedemo.item;

public class ItemVideoYoutube {
    private String titleVideo;
    private String thumbnail;
    private String id;
    private String titleChannel;

    public ItemVideoYoutube(String titleVideo, String thumbnail, String id, String titleChannel) {
        this.titleVideo = titleVideo;
        this.thumbnail = thumbnail;
        this.id = id;
        this.titleChannel = titleChannel;
    }

    public String getTitleVideo() {
        return titleVideo;
    }

    public void setTitleVideo(String titleVideo) {
        this.titleVideo = titleVideo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleChannel() {
        return titleChannel;
    }

    public void setTitleChannel(String titleChannel) {
        this.titleChannel = titleChannel;
    }
}
