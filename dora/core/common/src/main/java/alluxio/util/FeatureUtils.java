/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.util;

import alluxio.conf.Configuration;
import alluxio.conf.PropertyKey;
import alluxio.master.journal.JournalType;
import alluxio.master.metastore.MetastoreType;
import alluxio.worker.block.BlockStoreType;

/**
 * Utilities to detect features that Alluxio is running with.
 */
public final class FeatureUtils {
  /**
   * Utility to check the master journal type is embedded journal.
   *
   * @return true, if running with embedded journal
   */
  public static boolean isEmbeddedJournal() {
    return JournalType.EMBEDDED == Configuration.get(PropertyKey.MASTER_JOURNAL_TYPE);
  }

  /**
   * Utility to check the master inode metastore is rocks.
   *
   * @return true, if running with rocks
   */
  public static boolean isInodeStoreRocks() {
    if (Configuration.isSetByUser(PropertyKey.MASTER_INODE_METASTORE)) {
      return Configuration.get(PropertyKey.MASTER_INODE_METASTORE) == MetastoreType.ROCKS;
    }
    return Configuration.get(PropertyKey.MASTER_METASTORE) == MetastoreType.ROCKS;
  }

  /**
   * Utility to check the master block metastore is rocks.
   *
   * @return true, if running with rocks
   */
  public static boolean isBlockStoreRocks() {
    if (Configuration.isSetByUser(PropertyKey.MASTER_BLOCK_METASTORE)) {
      return Configuration.get(PropertyKey.MASTER_BLOCK_METASTORE) == MetastoreType.ROCKS;
    }
    return Configuration.get(PropertyKey.MASTER_METASTORE) == MetastoreType.ROCKS;
  }

  /**
   * Utility to check back delegation is enabled.
   *
   * @return true, if backup delegation is enabled
   */
  public static boolean isBackupDelegationEnabled() {
    return Configuration.getBoolean(PropertyKey.MASTER_BACKUP_DELEGATION_ENABLED);
  }

  /**
   * Utility to check daily backup is enabled.
   *
   * @return true, if daily backup is enabled
   */
  public static boolean isDailyBackupEnabled() {
    return Configuration.getBoolean(PropertyKey.MASTER_DAILY_BACKUP_ENABLED);
  }

  /**
   * Utility to check persistence black list is empty.
   *
   * @return true, if persistence black list is empty
   */
  public static boolean isPersistenceBlacklistEmpty() {
    return !Configuration.isSet(PropertyKey.MASTER_PERSISTENCE_BLACKLIST)
        || Configuration.getList(PropertyKey.MASTER_PERSISTENCE_BLACKLIST).isEmpty();
  }

  /**
   * Utility to check unsafe direct persistence is enabled.
   *
   * @return true, if unsafe direct persistence is enabled
   */
  public static boolean isUnsafeDirectPersistEnabled() {
    return Configuration.getBoolean(PropertyKey.MASTER_UNSAFE_DIRECT_PERSIST_OBJECT_ENABLED);
  }

  /**
   * Utility to check master audit logging is enabled.
   *
   * @return true, if master audir logging is enabled
   */
  public static boolean isMasterAuditLoggingEnabled() {
    return Configuration.getBoolean(PropertyKey.MASTER_AUDIT_LOGGING_ENABLED);
  }

  /**
   * Utility to check page store is enabled.
   * @return true, if page store is enabled
   */
  public static boolean isPageStoreEnabled() {
    return Configuration.get(PropertyKey.WORKER_BLOCK_STORE_TYPE) == BlockStoreType.PAGE;
  }
}
