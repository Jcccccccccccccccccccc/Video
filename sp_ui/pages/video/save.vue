<template>
	<view>
		<view class="add_picture">
			<label class="title">上传视频</label><br>
			<view class="many_photo" v-show="addVideo" @click="test">
				<image src="@/static/images/img_add.png"></image>
			</view>
			<view class="video-box" v-show="showVideo">
				<image src="@/static/images/img_close.png" @click="DelImg" class="close-img" style="height: 36rpx;">
				</image>
				<video :src="src" style="video"></video>
			</view>
		</view>
		<button class="sub-button" @click="subVieo">提交</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				src: '',
				showVideo: false,
				addVideo: true,
			}
		},
		methods: {
			//点击上传视频
			test: function() {
				var self = this;
				console.log("123123");
				uni.chooseVideo({
					count: 1,
					sourceType: ['camera', 'album'],
					success: function(res) {
						console.log("进来了");
						uni.uploadFile({
							url: 'http://localhost:8081/video/upload', //接口地址
							filePath: res.tempFilePath,
							name: 'file',
							
							success: (uploadFileRes) => {
								self.showVideo = true
								self.addVideo = false
								console.log('1张', uploadFileRes);
								self.src = uploadFileRes.data
							},
						});
					}
				});
			},
			//右上角删除视频
			DelImg() {
				this.src = '',
					this.showVideo = false,
					this.addVideo = true
			},
			async subVieo() {
				let url = this.src
				if (url) {
					let result = await uni.request({
						url: "http://localhost:8081/video/addVideo",
						method: "POST",
						data: {
							videoUrl: url
						}
					})
					result = result.data
					if (result.success) {
						uni.showToast({
							title: '添加成功',
							//将值设置为 success 或者直接不用写icon这个参数
							icon: 'success',
							//显示持续时间为 2秒
							duration: 2000
						})
						this.DelImg()
					} else {
						uni.showToast({
							title: '上传失败',
							//将值设置为 success 或者直接不用写icon这个参数
							icon: 'error',
							//显示持续时间为 2秒
							duration: 2000
						})
					}
					console.log(result);

				} else {
					uni.showToast({
						title: '请上传视频',
						//将值设置为 success 或者直接不用写icon这个参数
						icon: 'error',
						//显示持续时间为 2秒
						duration: 2000
					})
				}

			}
		}
	}
</script>

<style>
	/deep/uni-video {
		margin-top: 20rpx;
		margin-left: 5%;

	}

	.add_picture {
		width: 100%;
		height: 500rpx;
		background-color: #f8faf7;
		margin-top: 5rpx;
	}



	.many_photo {
		border: #8a8a8a 0.1rpx dashed;
		width: 300rpx;
		height: 300rpx;
		border-radius: 8rpx;
		margin: 30rpx;
	}

	.many_photo>image {
		width: 100rpx;
		height: 100rpx;
		margin: 30% 30%;
	}

	.video-box {
		width: 400rpx;
		height: 300rpx;
		border: #8a8a8a 0.1rpx solid;
		border-radius: 8rpx;
		margin: 45rpx 100rpx;
	}

	.video-box>video {
		width: 400rpx;
		height: 250rpx;
	}

	.video-box>image {
		display: block;
		height: 36rpx;
		width: 36rpx;
		margin-left: 351rpx;
		margin-bottom: 10rpx;
	}

	.sub-button {
		width: 500rpx;
		height: 70rpx;
		margin: 84rpx 120rpx;
		background-color: #66b94d;
		color: #f5fef2;
		line-height: 70rpx;
	}
</style>
