import { createApp } from 'vue';
import App from './App.vue';
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8081'; // Адрес бэкенда
createApp(App).mount('#app');
