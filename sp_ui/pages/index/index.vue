<template>
	<view>
		<view class="content">
			<video id="myVideo" class="video-box" :src="presentVideo.videoUrl" autoplay></video>
		</view>
		<view class="button-box">
			<image src="../../static/images/向左.png" @click="previousVideo"></image>
			<image src="../../static/images/评论.png" @click="skipComment"></image>
			<image src="../../static/images/向右.png" @click="nextVidedo"></image>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				videoSources: [],
				presentVideo: {},
				index: 0
			}
		},  // [0,1,2,3,4,5,6,7,8,9,10,11,12]
		methods: {
			//给定一个视频集合，从视频集合中随机抽取一个视频
			setPresentVideo(videoList) {
				let randomIndex = Math.floor(Math.random() * videoList.length)
				this.presentVideo = videoList[randomIndex]
				this.index = randomIndex;
				this.saveVideoId(this.presentVideo.videoId)
			},
			//下一个视频
			nextVidedo() {
				this.index = (this.index + 1) % this.videoSources.length
				this.presentVideo = this.videoSources[this.index]
				this.saveVideoId(this.presentVideo.videoId)
			},
			//上一个视频
			previousVideo() {
				if (this.index === 0) {
					this.index = this.videoSources.length - 1
				} else {
					this.index--
				}
				this.presentVideo = this.videoSources[this.index]
				this.saveVideoId(this.presentVideo.videoId)
			},
			//跳转到评论页
			skipComment() {
				uni.navigateTo({
					url: "/pages/comment/comment"
				})
			},
			//存储videoId到全局变量
			saveVideoId(videoId) {
				getApp().globalData.videoId = videoId
			}
		},
		async onLoad() {
			let result = await uni.request({
				url: "http://localhost:8081/video/getVideo",
				method: "GET"
			})
			let videoList = result.data.data.videos
			this.videoSources = videoList
			this.setPresentVideo(videoList)
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		height: 950rpx;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}

	.video-box {
		width: 100%;
		height: 950rpx;
	}

	.button-box {
		width: 100%;
		height: 150rpx;
		margin-top: 6rpx;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.button-box image {
		width: 15%;
		height: 125rpx;
		margin: 0 15px;
	}
</style>
