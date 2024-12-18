import axios from 'axios';

const API_URL = 'http://localhost:8081/railways';

export default {
    getRailways() {
        return axios.get(API_URL);
    },
    addRailway(railway) {
        return axios.post(API_URL, railway);
    },
    updateRailway(railway) {
        return axios.put(API_URL, railway);
    },
    deleteRailway(id) {
        return axios.delete(`${API_URL}/${id}`);
    },
};

