<template>
  <div>
    <h2>Список железнодорожных путей</h2>
    <ul>
      <li v-for="railway in railways" :key="railway.id">
        {{ railway.name }} ({{ railway.length_km }} км, {{ railway.country }})
        <span v-if="railway.is_operational">[Оперативный]</span>
        <button @click="deleteRailway(railway.id)">Удалить</button>
      </li>
    </ul>
  </div>
</template>

<script>
import railwayService from '@/services/railwayService';

export default {
  data() {
    return {
      railways: [],
    };
  },
  methods: {
    async fetchRailways() {
      const response = await railwayService.getRailways();
      this.railways = response.data;
    },
    async deleteRailway(id) {
      await railwayService.deleteRailway(id);
      this.fetchRailways();
    },
  },
  created() {
    this.fetchRailways();
  },
};
</script>
