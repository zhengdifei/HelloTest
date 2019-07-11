package com.hw.video.client;

import android.graphics.Bitmap;

public class VideoStreamingReceiverClient {
	private final VideoStreamingReceiverService service;

	public VideoStreamingReceiverClient(VideoStreamingReceiverService service) {
		this.service = service;
	}

	public Bitmap getNextBitmap() {
		return service.getNextBitmap();
	}

	public String getStatus() {
		return service.getStatus();
	}
	
	public void mutateExit(boolean flag) {
		service.mutateExit(flag);
	}
}
