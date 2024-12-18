<template>
  <div id="app">
    <h1>Управление железнодорожными путями</h1>
    <RailwayForm />
    <RailwayList />
  </div>
</template>

<script>
import RailwayList from '@/components/RailwayList.vue';
import RailwayForm from '@/components/RailwayForm.vue';

export default {
  components: {
    RailwayList,
    RailwayForm,
  },
};
</script>
