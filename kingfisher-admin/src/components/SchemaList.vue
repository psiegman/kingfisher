<template>
  <div>
    <h4 v-if="loading">Loading...</h4>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th>Key</th><th>Name</th><th>Description</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="schema in schemas"
        :key="schema.key">
        <td><router-link :to="'/content/schema/' + schema.key">{{ schema.key }}</router-link></td>
        <td><router-link :to="'/content/schema/' + schema.key">{{ schema.name }}</router-link></td>
        <td>{{ schema.description }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  import { ALL_SCHEMAS_QUERY } from '../constants/graphql';

  export default {
    name: 'SchemaList',
    data () {
      return {
        schemas: [],
        loading: 0
      };
    },
    apollo: {
      schemas: {
        query: ALL_SCHEMAS_QUERY,
        update: data => {return data.cms.cmsContent.schemas}
      },
    }
  };
</script>
