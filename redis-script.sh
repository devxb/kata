#!/bin/bash

# Redis ¿¿ ¿¿ ¿¿
REDIS_HOST="localhost"
REDIS_PORT=6379

# ¿¿¿ ¿¿ ¿ ¿¿ ¿¿
MESSAGE_SIZE_KB=10
MESSAGE_SIZE_BYTES=$((MESSAGE_SIZE_KB * 1024))
NUM_MESSAGES=10000

# ¿¿¿ ¿¿ ¿¿
STREAM_NAME="mystream"

# ¿¿ ¿¿¿ ¿¿ ¿¿
generate_message() {
  local size=$1
  tr -dc 'a-zA-Z0-9' </dev/urandom | head -c $size
}

# ¿¿¿ ¿¿
for i in $(seq 1 $NUM_MESSAGES); do
  message=$(generate_message $MESSAGE_SIZE_BYTES)
  redis-cli -h $REDIS_HOST -p $REDIS_PORT XADD $STREAM_NAME "*" message "$message"
done

echo "$NUM_MESSAGES messages of ${MESSAGE_SIZE_KB}KB have been added to Redis stream $STREAM_NAME."

