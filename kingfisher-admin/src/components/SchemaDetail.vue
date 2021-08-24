<template>
<div>
<ul class="breadcrumbs">
  <li class="breadcrumb">
    <router-link :to="'/content/schema/' + schemaKey">{{ schemaKey }}</router-link>
  </li>
</ul>
<div v-if="schema">
<h1>The catalogs of {{ schema.name }}</h1>
<div class="catalog-tileset">
<div class="catalog-tile" v-for="catalog in schema.catalogs" v-if="schema" :key="catalog.key">
    <router-link :to="'/content/catalog/' + schema.key + '/' + catalog.key">
      <svg width="80" height="80" :data-jdenticon-value="catalog.key"></svg>
    </router-link>
    <h2><router-link :to="'/content/catalog/' + schema.key + '/' + catalog.key">{{ catalog.name, catalog.key | fnb }}</router-link></h2>
    <br/>
    <ul>
      <li v-for="catalogVersion in catalog.catalogVersions" :key="catalogVersion.key">
        <router-link :to="'/content/contentitem/' + schema.key + '/' + catalog.key + '/' + catalogVersion.key">{{ catalogVersion.name, catalogVersion.key | fnb }}</router-link> ({{ catalogVersion.nrContentItems }} content items)
      </li>
    </ul>
</div>
</div>
</div>
</div>
</template>

<script>
import jdenticon from 'jdenticon';
import { SCHEMA_DETAIL_QUERY } from "../constants/graphql";

export default {
  name: "SchemaDetail",
  props: ["schemaKey"],
  data() {
    return {
      schema: null,
    };
  },
  mounted: function() {
     jdenticon();
  },
  apollo: {
    schema: {
      query: SCHEMA_DETAIL_QUERY,
      variables() {
        return {
          schemaKey: this.schemaKey
        };
      },
      update: data => JSON.parse(JSON.stringify(data.cms.cmsContent.schema)),
    }
  }
};
</script>
