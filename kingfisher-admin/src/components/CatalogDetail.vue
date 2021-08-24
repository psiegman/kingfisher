<template>
<div>
<ul class="breadcrumbs">
  <li class="breadcrumb">
    <router-link :to="'/content/schema/' + schemaKey">{{ schemaKey }}</router-link>
  </li>
</ul>
<div v-if="schema">
<svg width="80" height="80" :data-jdenticon-value="schema.catalog.key"></svg>
<h1>Catalog {{ schema.catalog.key, schema.catalog.name | fnb }}</h1>
<h2 v-if="schema.catalog.description">Description</h2>
{{ schema.catalog.description }}
Key: {{ schema.catalog.key}}<br/>
Name: {{ schema.catalog.name }}<br/>
Description <input v-model="schema.catalog.description"/>
<p/>
<h2>CatalogVersions</h2>
<ul>
      <li v-for="catalogVersion in schema.catalog.catalogVersions" :key="catalogVersion.key">
              <router-link :to="'/content/contentitem/' + schema.key + '/' + schema.catalog.key + '/' + catalogVersion.key">{{ catalogVersion.key, catalogVersion.name | fnb }}</router-link>
              ( {{ catalogVersion.nrContentItems }} content items)
      </li>
</ul>
</div>
</div>
</template>

<script>
import { CATALOG_DETAIL_QUERY, CATALOG_DETAIL_MUTATION } from "../constants/graphql";

export default {
  name: "CatalogDetail",
  props: ["schemaKey", "catalogKey"],
  data() {
    return {
      schema: null,
      loading: 0
    };
  },
  // mounted: function() {
  //    jdenticon();
  // },
  apollo: {
    schema: {
      query: CATALOG_DETAIL_QUERY,
      variables() {
        return {
          schemaKey: this.schemaKey,
          catalogKey: this.catalogKey
        };
      },
      fetchPolicy: 'cache-and-network',
      update: data => JSON.parse(JSON.stringify(data.cms.cmsContent.schema)),
    }
  }
};
</script>
