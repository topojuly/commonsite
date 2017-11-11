import React, { Component, PureComponent } from 'react';
import classNames from 'classnames';
import Input from 'input';
import Popover from 'popover';
import formatDate from 'zan-utils/date/formatDate';
import parseDate from 'zan-utils/date/parseDate';
import getWidth from 'utils/getWidth';

import MonthPanel from './month/MonthPanel';
import PanelFooter from './common/PanelFooter';
import { CURRENT } from './utils/';
import { dayStart } from './utils/date';
import {
  noop,
  popPositionMap,
  commonProps,
  commonPropTypes
} from './constants/';

function extractStateFromProps(props) {
  let showPlaceholder;
  let selected;
  let actived;
  const { format, value, defaultValue } = props;

  if (value) {
    const tmp = parseDate(value, format);
    if (tmp) {
      showPlaceholder = false;
      selected = actived = tmp;
    } else {
      console.warn("date and format don't match."); // eslint-disable-line
      showPlaceholder = true;
      selected = actived = dayStart();
    }
  } else {
    showPlaceholder = true;
    if (defaultValue) {
      actived = parseDate(defaultValue, format);
    } else {
      actived = dayStart();
    }
    selected = actived = parseDate(actived, format);
  }

  return {
    value: formatDate(selected, format),
    actived,
    selected,
    openPanel: false,
    showPlaceholder
  };
}

class MonthPicker extends (PureComponent || Component) {
  static PropTypes = {
    ...commonPropTypes
  };

  static defaultProps = {
    ...commonProps,
    placeholder: '请选择月份',
    format: 'YYYY-MM'
  };

  constructor(props) {
    super(props);
    this.state = extractStateFromProps(props);
  }

  componentWillReceiveProps(next) {
    const state = extractStateFromProps(next);
    this.setState(state);
  }

  onChangeMonth = val => {
    this.setState({
      actived: val
    });
  };

  onSelectMonth = val => {
    const { onClick } = this.props;
    this.setState({
      selected: val,
      actived: val
    });

    onClick && onClick(val);
  };

  onClearInput = evt => {
    evt.stopPropagation();
    this.props.onChange('');
  };

  onConfirm = () => {
    const { format } = this.props;
    const { selected } = this.state;
    const value = formatDate(selected, format);

    this.setState({
      value,
      openPanel: false,
      showPlaceholder: false
    });
    this.props.onChange(value);
  };

  isDisabled = val => {
    const year = this.state.actived.getFullYear();
    const dateStr = `${year}-${val + 1}`;
    const ret = parseDate(dateStr, 'YYYY-MM');

    const { disabledDate, min, max, format } = this.props;

    if (disabledDate && disabledDate(ret)) return true;
    if (min && ret < parseDate(min, format)) return true;
    if (max && ret > parseDate(max, format)) return true;

    return false;
  };

  renderPicker() {
    const state = this.state;
    const props = this.props;

    let monthPicker;
    if (state.openPanel) {
      monthPicker = (
        <div className="month-picker" ref={ref => (this.picker = ref)}>
          <MonthPanel
            actived={state.actived}
            selected={state.selected}
            onChange={this.onChangeMonth}
            onSelect={this.onSelectMonth}
            disabledDate={this.isDisabled}
          />
          <PanelFooter
            buttonText={props.confirmText}
            linkText="当前月"
            linkCls="link--current"
            onClickLink={() => this.onSelectMonth(CURRENT)}
            onClickButton={this.onConfirm}
          />
        </div>
      );
    }

    return monthPicker;
  }

  togglePicker = () => {
    const { onOpen, onClose, disabled } = this.props;
    const openPanel = !this.state.openPanel;

    if (disabled) return;

    openPanel ? onOpen && onOpen() : onClose && onClose();
    this.setState({
      openPanel: !this.state.openPanel
    });
  };

  render() {
    const state = this.state;
    const props = this.props;
    const wrapperCls = `${props.prefix}-datetime-picker ${props.className}`;
    const inputCls = classNames({
      'picker-input': true,
      'picker-input--filled': !state.showPlaceholder,
      'picker-input--disabled': props.disabled
    });
    const widthStyle = getWidth(props.width);

    return (
      <div style={widthStyle} className={wrapperCls}>
        <Popover
          cushion={5}
          visible={state.openPanel}
          onVisibleChange={this.togglePicker}
          className={`${props.prefix}-datetime-picker-popover ${props.className}-popover`}
          position={popPositionMap[props.popPosition.toLowerCase()]}
        >
          <Popover.Trigger.Click>
            <div style={widthStyle} className={inputCls}>
              <Input
                name={props.name}
                value={state.showPlaceholder ? props.placeholder : state.value}
                onChange={noop}
                disabled={props.disabled}
              />

              <span className="zenticon zenticon-calendar-o" />
              <span
                onClick={this.onClearInput}
                className="zenticon zenticon-close-circle"
              />
            </div>
          </Popover.Trigger.Click>
          <Popover.Content>{this.renderPicker()}</Popover.Content>
        </Popover>
      </div>
    );
  }
}

export default MonthPicker;
