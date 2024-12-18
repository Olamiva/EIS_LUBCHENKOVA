<template>
  <div>
    <h2>Добавить/Обновить железнодорожный путь</h2>
    <form @submit.prevent="submitRailway">
      <input v-model="railway.name" placeholder="Название пути" required />
      <input v-model.number="railway.length_km" placeholder="Длина пути (км)" type="number" step="0.1" required />
      <input v-model="railway.country" placeholder="Страна" required />
      <label>
        Оперативный:
        <input type="checkbox" v-model="railway.is_operational" />
      </label>
      <button type="submit">Сохранить</button>
    </form>
  </div>
</template>

<script>
import railwayService from '@/services/railwayService';

export default {
  data() {
    return {
      railway: {
        id: null,
        name: '',
        length_km: 0,
        country: '',
        is_operational: false,
      },
    };
  },
  methods: {
    async submitRailway() {
      if (this.railway.id) {
        await railwayService.updateRailway(this.railway);
      } else {
        await railwayService.addRailway(this.railway);
      }
      this.clearForm();
    },
    clearForm() {
      this.railway = {
        id: null,
        name: '',
        length_km: 0,
        country: '',
        is_operational: false,
      };
    },
  },
};
</script>
