import { createApp} from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import axios from './axios.config.js'


const app = createApp(App)
app.config.globalProperties.$http = axios;
app.use(ElementPlus)

app.mount('#app')


