<template>
  <div>
    <h2>Список железнодорожных путей</h2>
    <ul>
      <li v-for="railway in railways" :key="railway.id">
        {{ railway.name }} ({{ railway.country }}) - Длина: {{ railway.length_km }} км.
        В эксплуатации: {{ railway.is_operational ? "Да" : "Нет" }}
        <button @click="$emit('edit', railway)">Редактировать</button>
        <button @click="deleteRailway(railway.id)">Удалить</button>
      </li>
    </ul>
  </div>
</template>

<script>
import railwayService from "@/services/railwayService";

export default {
  data() {
    return {
      railways: [],
    };
  },
  methods: {
    async fetchRailways() {
      this.railways = (await railwayService.getRailways()).data;
    },
    async deleteRailway(id) {
      await railwayService.deleteRailway(id);
      this.fetchRailways();
    },
  },
  mounted() {
    this.fetchRailways();
  },
};
</script>
