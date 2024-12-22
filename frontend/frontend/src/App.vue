<template>
  <div id="app">
    <h1>Управление железнодорожными путями</h1>
    <RailwayForm @refresh="refreshList" ref="form" />
    <RailwayList @edit="editRailway" ref="list" />
  </div>
</template>

<script>
import RailwayForm from "@/components/RailwayForm.vue";
import RailwayList from "@/components/RailwayList.vue";

export default {
  components: {
    RailwayForm,
    RailwayList,
  },
  methods: {
    refreshList() {
      this.$refs.list.fetchRailways();
    },
    editRailway(railway) {
      this.$refs.form.editRailway(railway);
    },
  },
};
</script>
