<template>
	<view>
		<view class="head-box">
			<view class="box-view">
				<text>共{{commentList.length}}条评论</text>
			</view>
			<view class="box-view">
				<button class="mini-btn box-button" type="primary" size="mini" @click="showDiv">发表评论</button>
			</view>
		</view>
		<view v-for="comment in commentList" class="comment-line">
			<view class="line-left">
				<image src="../../static/images/下载.jpg" class="image-style"></image>
			</view>
			<view class="line-right">
				<text class="line-right-nick">{{comment.nickName}}</text>
				<text>{{comment.content}}</text>
				<text>{{comment.commentTime}}</text>
			</view>
		</view>
		<view>
		</view>
		<view :hidden="dialog.isShow" class="popup_content">
			<view class="popup_head">
				<view>
					<button class="u-reset-button info-button" @click="hideDiv">取消</button>
				</view>
				<view>
					<button class="u-reset-button success-button" @click="submitFeedback">发布</button>
				</view>
			</view>
			<view class="popup_textarea_item">
				<textarea class="popup_textarea" placeholder='友善是交流的起点' v-model="commentData.content">
				</textarea>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				videoId: '',
				commentList: [],
				dialog: {
					isShow: true,
				},
				commentData: {
					content: '',
					nickName: 'sp'
				}
			}
		},
		methods: {
			//获取当前视频的评论
			getComments() {
				this.videoId = getApp().globalData.videoId
				uni.request({
					url: `http://localhost:8081/comment/getComment/${this.videoId}`,
					method: "GET"
				}).then(result => {
					this.commentList = result.data.data.comments
				})
			},
			showDiv() { // 显示输入弹出框
				this.dialog.isShow = false;
			},
			hideDiv() { // 隐藏输入弹出框
				this.dialog.isShow = true;
				this.commentData.content = ''
			},
			async submitFeedback() { // 提交反馈
				let comment = this.commentData
				comment.videoId = this.videoId
				let result = await uni.request({
					url: `http://localhost:8081/comment/saveComment`,
					method: "POST",
					data: comment
				})
				if (result.data.success) {
					uni.showToast({
						title: '发布成功',
						//将值设置为 success 或者直接不用写icon这个参数
						icon: 'success',
						//显示持续时间为 2秒
						duration: 2000
					})
					this.getComments();
					this.hideDiv()
				} else {
					uni.showToast({
						title: '评论失败',
						//将值设置为 success 或者直接不用写icon这个参数
						icon: 'error',
						//显示持续时间为 2秒
						duration: 2000
					})
				}
			}
		},
		onShow() {
			this.getComments()
		}
	}
</script>

<style>
	.head-box {
		width: 100%;
		height: 100rpx;
		margin-top: 5rpx;
		display: flex;
		align-items: center;
		justify-content: space-between;
		border-bottom: 1rpx solid #AED0ED;
	}

	.box-view {
		height: 100%;
		margin: 5rpx 15rpx;
		line-height: 100rpx;
		text-align: center;
	}

	.box-button {
		margin-top: 20rpx;
		margin-right: 0;
	}


	.popup_content {
		position: fixed;
		width: 100%;
		height: 350upx;
		bottom: 0px;
		border: 1px solid #ececec;
		background-color: white;
		z-index: 1002;
		overflow: auto;
	}

	.popup_head {
		width: 100%;
		height: 60rpx;
		display: flex;
		align-items: center;
		justify-content: space-between;
	}

	.popup_textarea_item {
		height: 260upx;
		width: 100%;
		border-top: #ececec 1px dashed;
	}

	.popup_textarea {
		width: 100%;
		font-size: 26upx;
	}

	.comment-line {
		width: 100%;
		height: 150rpx;
		margin-top: 10rpx;
		flex-flow: row;
		justify-content: flex-start;
		display: flex;
	}

	.line-left {
		width: 20%;
		height: 100%;
	}

	.line-right {
		width: 80%;
		height: 100%;
	}

	.line-right text {
		display: block;
		line-height: 25px;
	}

	.line-right-nick {
		padding-left: 75rpx;
		color: #4399FC;
	}

	.image-style {
		width: 65rpx;
		height: 65rpx;
		border-radius: 50%;
		margin-top: 5rpx;
		margin-left: 60rpx;
	}

	.u-reset-button {
		font-size: inherit;
		line-height: inherit;
		background-color: transparent;
		display: inline;
		margin: 0 10rpx;
	}

	.info-button {
		color: #8d8d8d;
	}

	.success-button {
		color: #4399FC;
	}

	.u-reset-button::after {
		border: none;
	}
</style>
