#!/bin/bash

# Copyright (C) 2020 Timothy "ZeevoX" Langer
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

CHANGELOG="$(./scripts/changelog.sh)"

rm -f app/build/outputs/apk/debug/output.json
# shellcheck disable=SC2012
APK_NAME_TALOS=$(ls -1 app/build/outputs/apk/debug/ | tr -d '\n')

## Deploy to zeevox.net

curl -F "dir=/files/CI/TalosRowing/" -F "file=@${APK_NAME_TALOS}" -u "${WEBSERVER_LOGIN}" https://dl.zeevox.net/admin/upload_file.php

## Deploy to Telegram

curl -F chat_id="-1001357118452" -F text="<a href=\"https://zeevox.net/files/CI/TalosRowing/${APK_NAME_TALOS}\">${APK_NAME_TALOS}</a>" -F parse_mode="HTML" -F disable_web_page_preview="false" https://api.telegram.org/bot${BOT_TOKEN}/sendMessage
curl -F chat_id="-1001357118452" -F text="${CHANGELOG}" -F parse_mode="HTML" -F disable_web_page_preview="true" https://api.telegram.org/bot${BOT_TOKEN}/sendMessage

## Print changelog

./scripts/changelog.sh
