import { createApp} from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import axios from './axios.config.js'

import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';

import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';

VMdPreview.use(githubTheme);
VueMarkdownEditor.use(githubTheme);
const app = createApp(App)
app.config.globalProperties.$http = axios;
app.use(ElementPlus)

app.use(VueMarkdownEditor);
app.use(VMdPreview);

app.mount('#app')


