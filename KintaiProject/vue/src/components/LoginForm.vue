<template>
	<div class="login-container">
	<!-- ヘッダー -->
	<CommonHeader title="ログイン" />

	<!-- メッセージエリア -->
	<div class="message-area" style="border: 1px solid black;">{{ message }}</div>

	<!-- 入力エリア -->
	<div class="form-area">
		<div class="form-row">
		<label for="userId">ユーザID</label>
		<input type="text" id="userId" v-model="userId" />
	</div>
	<div class="form-row">
		<label for="password">パスワード</label>
		<input type="password" id="password" v-model="password" />
	</div>
	</div>

	<!-- ボタンエリア -->
	<div class="button-area">
		<button @click="handleLogin">ログイン</button>
		<button @click="handleClear">クリア</button>
		<button @click="handleTest">バックエンド検証</button>
		<p v-if="department">{{ department.departmentId }}</p>
		<p v-if="department">{{ department.departName }}</p>
	</div>
	</div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import CommonHeader from '@/components/common/CommonHeader.vue'  // 共通ヘッダー
// import { mockLogin } from '@/mocks/mockApi.js' //モック
import axios from 'axios';



const userId = ref('')
const password = ref('')
const message = ref('ユーザIDとパスワードを入力してください')

const router = useRouter()

// ここからログイン認証のモック
/** const handleLogin = async () => {
	if (!userId.value || !password.value) {
		message.value = 'ユーザIDとパスワードは必須項目です'
		return
	}

	try {
		// fetch の代わりにモック関数を呼ぶ
		// mockApi.jsに記載したユーザIDとパスワードでログインできる
		const result = await mockLogin({
			userId: userId.value,
			password: password.value
		})

		if (result.success) {
			// ログイン成功時
			sessionStorage.setItem('user', JSON.stringify(result.user))
			router.push('/menu')
		} else {
			message.value = 'ユーザIDまたはパスワードが間違っています'
		}
	} catch (err) {
		console.error(err)
		message.value = '通信エラーが発生しました'
	}
} */

const handleLogin = async () => {
  if (!userId.value || !password.value) {
    message.value = 'ユーザIDとパスワードは必須項目です'
    return
  }

  try {
    const res = await axios.post(
      "http://localhost:8080/api/login",
      {
        userId: userId.value,
        password: password.value
      }
    )

    if (res.data.success) {
      // ログイン成功
      sessionStorage.setItem("loginUser", JSON.stringify(res.data.user))
      router.push('/menu')
    } else {
      message.value = res.data.message
    }

  } catch (err) {
    console.error(err)
    message.value = '通信エラーが発生しました'
  }
}

const handleClear = () => {
	userId.value = ''
	password.value = ''
	message.value = 'ユーザIDとパスワードを入力してください'
}

async function handleTest() {
	const res = await axios.get("http://localhost:8080/departmentMsts/1")
	department.value = res.data
}



const department = ref(null)


// ここまで、モック用 */
</script>

<style scoped>

/* メッセージエリア */
.message-area {
	padding: 10px;
	border-bottom: 1px solid #000;
	font-size: 14px;
}

/* 入力フォームエリア */
.form-area {
	padding: 20px 30px 10px;
}

.form-row {
	display: flex;
	align-items: center;
	justify-content: center;
	margin-bottom: 15px;
}

/* 入力欄名(ユーザIDとか) */
label {
	width: 100px;
	font-size: 14px;
	text-align: right;
	margin-right: 8px;
}

/* 入力欄 */
input {
	flex: none;
	width: 50%;
	padding: 5px;
	font-size: 14px;
	border: 1px solid #000;
}

/* ボタンエリア */
.button-area {
	display: flex;
	justify-content: center;
	gap: 20px;
	padding: 20px;
}

button {
	padding: 8px 20px;
	background-color: #d9d9d9;
	border: 1px solid #000;
	cursor: pointer;
}

button:hover {
	background-color: #c0c0c0;
}
</style>
