<template>
<div>
    <ul class="breadcrumbs">
  <li class="breadcrumb">
    <router-link :to="'/content/schema/' + schemaKey">{{ schemaKey }}</router-link>
  </li>
  <li class="breadcrumb">
    <router-link :to="'/content/catalog/' + schemaKey + '/' + catalogKey">{{ catalogKey }}</router-link>
  </li>
</ul>
<div v-if="schema">
<h1>A Schema: {{ schemaKey }}</h1>
<form>
  <div class="form-group">
    <label for="schemaKey">Key</label>
    <input type="text" class="form-control" id="schemaKey" :value="schema.key"/>
  </div>
  <div class="form-group">
    <label for="schemaName">Name</label>
    <input type="text" class="form-control" id="schemaName" :value="schema.name"/>
  </div>
  <div class="form-group">
    <label for="schemaDescription">Description</label>
    <input type="text" class="form-control" id="schemaDescription" :value="schema.description"/>
  </div>
  </form>
</div>
</div>
</template>

<script>
import { SCHEMA_DETAIL_QUERY } from "../constants/graphql";

export default {
  name: "SchemaEdit",
  props: ["schemaKey"],
  data() {
    return {
      schema: null,
      loading: 0
    };
  },
  apollo: {
    schema: {
      query: SCHEMA_DETAIL_QUERY,
      variables() {
        return {
          schemaKey: this.schemaKey
        };
      },
      update: data => {
        return data.cms.cmsContent.schema;
      }
    }
  }
};
</script>
