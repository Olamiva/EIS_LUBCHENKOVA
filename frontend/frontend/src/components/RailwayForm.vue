<template>
  <div>
    <h2>Добавить/Обновить железнодорожный путь</h2>
    <form @submit.prevent="submitRailway">
      <input
          v-model="railway.name"
          placeholder="Название пути"
          required
      />
      <input
          v-model.number="railway.length_km"
          type="number"
          placeholder="Длина (км)"
          required
      />
      <input
          v-model="railway.country"
          placeholder="Страна"
          required
      />
      <label>
        В эксплуатации:
        <input
            type="checkbox"
            v-model="railway.is_operational"
            @change="updateIsOperational"
        />
      </label>
      <button type="submit">Сохранить</button>
    </form>
  </div>
</template>

<script>
import railwayService from "@/services/railwayService";

export default {
  data() {
    return {
      railway: {
        id: null,
        name: "",
        length_km: 0,
        country: "",
        is_operational: false,
      },
    };
  },
  methods: {
    updateIsOperational(event) {
      this.railway.is_operational = event.target.checked || false;
    },
    async submitRailway() {
      this.railway.is_operational = !!this.railway.is_operational;
      if (this.railway.id) {
        await railwayService.updateRailway(this.railway);
      } else {
        await railwayService.addRailway(this.railway);
      }
      this.clearForm();
      this.$emit("refresh");
    },
    clearForm() {
      this.railway = {
        id: null,
        name: "",
        length_km: 0,
        country: "",
        is_operational: false,
      };
    },
    editRailway(railway) {
      this.railway = {...railway};
    },
  },
};
</script>
