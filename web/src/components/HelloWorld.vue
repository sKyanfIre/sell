<template>
  <div>
    <el-form
      :model="loginForm"
      status-icon
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="ruleForm"
    >
      <el-form-item label="账号" prop="name">
        <el-input type="text" v-model="loginForm.name" autocomplete="off" placeholder="请输入账号"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="loginForm.pass" autocomplete="off" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {

  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.loginForm.pass !== '') {
          this.$refs.ruleForm.validateField('pass')
        }
        callback()
      }
    }
    var validateName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        name: '',
        pass: ''
      },
      rules: {
        pass: [{ validator: validatePass, trigger: 'blur' }],
        name: [{ validator: validateName, trigger: 'blur' }]
      }
    }
  },
  methods: {
    submitForm (formName) {
      // alert('submit!')
      this.$message.success('登录成功')
    }
  }

}
</script>

<style scoped>
.ruleForm {
  width: 500px
}
</style>
