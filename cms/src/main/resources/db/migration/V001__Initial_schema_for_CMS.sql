/*
drop table content_catalog if exists;
drop table catalog_version if exists;
drop table content_item if exists;
drop table content_type if exists;
drop table field_definition if exists;
drop table schema if exists;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table content_catalog (id bigint not null, description varchar(255), key varchar(255), schema_id bigint not null, primary key (id));
create table catalog_version (id bigint not null, description varchar(255), key varchar(255), content_catalog_id bigint not null, primary key (id));
create table content_item (id bigint not null, key varchar(255), catalog_version_id bigint not null, primary key (id));
create table content_type (id bigint not null, description varchar(255), key varchar(255), schema_id bigint not null, primary key (id));
create table field_definition (id bigint not null, field_type varchar(255) not null, key varchar(255), sequence bigint, content_type_id bigint, primary key (id));
create table schema (id bigint not null, description varchar(255), key varchar(255), primary key (id));

alter table catalog_version add constraint UKpgq5e0f8d82woq19sak5c2rca unique (key, content_catalog_id);
alter table content_type add constraint UK1v9l9vmi8c8iwp81m7k5rg6jf unique (key, schema_id);
alter table field_definition add constraint UKa2iacov80jt7gyhbr03hila2d unique (key, content_type_id);
alter table schema add constraint UKj4taw2dj8wwl6hogd0unlhrdg unique (key);
alter table content_catalog add constraint FK782uggglbhkk7yokhpairg3g1 foreign key (schema_id) references schema;
alter table catalog_version add constraint FK11vkhwrblx2yyk2jmhdi5qxxh foreign key (content_catalog_id) references content_catalog;
alter table content_item add constraint FKon0t0murh345ksjt5mcocogex foreign key (catalog_version_id) references catalog_version;
alter table content_type add constraint FK9s1nxi7xs7ma58ti560hth51u foreign key (schema_id) references schema;
alter table field_definition add constraint FKldj4neo4raqmk438rtykght1u foreign key (content_type_id) references content_type;
*/