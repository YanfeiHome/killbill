/*
 * Copyright 2010-2013 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.junction.glue;

import org.skife.config.ConfigSource;

import com.ning.billing.GuicyKillbillTestNoDBModule;
import com.ning.billing.junction.dao.BlockingStateDao;
import com.ning.billing.junction.dao.MockBlockingStateDao;
import com.ning.billing.mock.glue.MockNonEntityDaoModule;
import com.ning.billing.mock.glue.MockTagModule;
import com.ning.billing.util.bus.InMemoryBusModule;

public class TestJunctionModuleNoDB extends TestJunctionModule {

    public TestJunctionModuleNoDB(final ConfigSource configSource) {
        super(configSource);
    }

    @Override
    public void installBlockingStateDao() {
        bind(BlockingStateDao.class).toInstance(new MockBlockingStateDao());
    }

    @Override
    protected void configure() {
        super.configure();

        install(new GuicyKillbillTestNoDBModule());
        install(new MockNonEntityDaoModule());
        install(new InMemoryBusModule(configSource));
        install(new MockTagModule());
    }
}
