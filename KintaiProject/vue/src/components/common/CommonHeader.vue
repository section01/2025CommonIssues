<template>
  <header class="common-header">
    <!-- 左側：画面名 -->
    <div class="header-left">
      {{ title }}
    </div>

    <!-- 右側：ユーザ情報（必要なときだけ表示） -->
    <div class="header-right" v-if="showUser && user">
      <span>役職：{{ roleName }}</span>
      <span>名前：{{ user.empLname }} {{ user.empFname }}</span>
      <span>所属チーム：{{ "セクション1" }}</span>
    </div>
  </header>
</template>

<script>
export default {
  name: "CommonHeader",
  props: {
    title: { type: String, required: true }, // 画面名
    showUser: { type: Boolean, default: false }, // ユーザ情報を表示するか
    user: { type: Object, required: false }
    },
      computed: {
          roleName() {
              if (!this.user || !this.user.role) return ""

              const roleMap = {
                  1: "社員",
                  2: "チーフ",
                  3: "リーダー",
                  4: "アシスタントマネージャー",
                  5: "マネージャー"
              }
            return roleMap[this.user.role] || "不明"
          }
       }
}
</script>

<style scoped>
.common-header {
  width: 100%;
  height: 60px;
  background-color: #8ebc75; /* 背景色 */
  color: white;
  display: flex;
  justify-content: space-between; /* 左右に配置 */
  align-items: center;
  padding: 0 20px;
  box-sizing: border-box;
  border: 1px solid #000;
}
.header-left {
  font-size: 18px;
  font-weight: bold;
}
.header-right span {
  margin-left: 15px;
  font-size: 14px;
}
</style>