<template>
  <div>
    <div>
      <input
        v-model="key"
        type="text"
        placeholder="The Key of the schema">
      <input
        v-model="name"
        type="text"
        placeholder="The name of the link">
      <input
        v-model="description"
        type="text"
        placeholder="A description for the schema">
    </div>
    <button @click="createSchema()">Submit</button>
  </div>
</template>

<script>
import { SCHEMA_CREATE_MUTATION } from "../constants/graphql";
export default {
  name: "SchemaCreate",
  data() {
    return {
      key: '',
      name: '',
      description: ''
    };
  },
  methods: {
    createSchema() {
      const { key, name, description } = this.$data;
      this.$apollo.mutate({
        mutation: SCHEMA_CREATE_MUTATION,
        variables: {
          key,
          name,
          description
        }
      });
    }
  }
};
</script>