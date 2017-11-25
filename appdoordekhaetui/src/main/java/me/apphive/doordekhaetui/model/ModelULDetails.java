package me.apphive.doordekhaetui.model;

/**
 * Created by Rz Rasel 2017-11-19.
 */

public class ModelULDetails {
    private String title;
    private String description;
    private String channelLogo;
    private String streamUrl;

    public void setTitle(String argTitle) {
        this.title = argTitle;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String argDescription) {
        this.description = argDescription;
    }

    public String getDescription() {
        return this.description;
    }

    public void setChannelLogo(String argChannelLogo) {
        this.channelLogo = argChannelLogo;
    }

    public String getChannelLogo() {
        return this.channelLogo;
    }

    public void setStreamUrl(String argStreamUrl) {
        this.streamUrl = argStreamUrl;
    }

    public String getStreamUrl() {
        return this.streamUrl;
    }
}