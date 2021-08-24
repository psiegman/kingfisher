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
<svg width="80" height="80" :data-jdenticon-value="schema.catalog.key"></svg>
<h1>The ContentItems of {{ schema.key }}/{{ schema.catalog.key }}/{{ schema.catalog.catalogVersion.key }}</h1>
<table class="table table-striped table-hover">
  <thead>
    <th>Key</th>
    <th>Name</th>
    <th>ContentType</th>
  </thead>
  <tbody>
    <tr v-for="contentItem in schema.catalog.catalogVersion.contentItems" :key="contentItem.key">
      <td><router-link :to="'/content/contentitem/' + schema.key + '/' + schema.catalog.key + '/' + schema.catalog.catalogVersion.key + '/' + contentItem.key">{{ contentItem.key }}</router-link></td>
      <td>{{ contentItem.name }}</td>
      <td><router-link :to="'/content/contenttype/' + schemaKey + '/' + contentItem.contentType.key">{{ contentItem.contentType.key }}</router-link></td>
    </tr>
  </tbody>
</table>
</div>
</div>
</template>

<script>
import jdenticon from 'jdenticon';
import Quill from 'quill';

import { CONTENT_ITEM_LIST_QUERY } from "../constants/graphql";

export default {
  name: "ContentItemList",
  props: ["schemaKey", "catalogKey", "catalogVersionKey"],
  data() {
    return {
      schema: null,
      loading: 0
    };
  },
  mounted: function() {
     jdenticon();
  },
  updated: function() {
     jdenticon();
  },
  apollo: {
    schema: {
      query: CONTENT_ITEM_LIST_QUERY,
      variables() {
        return {
          schemaKey: this.schemaKey,
          catalogKey: this.catalogKey,
          catalogVersionKey: this.catalogVersionKey
        };
      },
      update: data => {
        return data.cms.cmsContent.schema;
      }
    }
  }
};
</script>
